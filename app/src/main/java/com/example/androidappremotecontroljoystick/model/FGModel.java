package com.example.androidappremotecontroljoystick.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FGModel {
    Thread t;
    Socket fgConnection;
    PrintWriter writer;
    BlockingQueue<Runnable> dispatchQueue = new LinkedBlockingQueue<>();
    boolean stop = false;

    /**
     * Sets the aileron value in FG.
     *
     * @param aileron value to set.
     */
    public void setAileron(double aileron) {
        send("aileron ", String.valueOf(aileron));
    }

    /**
     * Sets the elevator value in FG.
     *
     * @param elevator value to set.
     */
    public void setElevator(double elevator) {
        send("elevator ", String.valueOf(elevator));
    }

    /**
     * Sets the rudder value in FG.
     *
     * @param rudder value to set.
     */
    public void setRudder(double rudder) {
        send("rudder ", String.valueOf(rudder));
    }

    /**
     * Sets the throttle value in FG.
     *
     * @param throttle value to set.
     */
    public void setThrottle(double throttle) {
        send("current-engine/throttle ", String.valueOf(throttle));
    }

    /**
     * Connects to FG.
     *
     * @param host - ip address for the socket.
     * @param port - port for the socket.
     */
    public void connect(String host, int port) {
        try {
            fgConnection = new Socket(host, port);
            writer = new PrintWriter(fgConnection.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the communication with FG.
     */
    public void disconnect() {
        stop = true;
        t.interrupt();
    }

    /**
     * Runs commands from dispatchQueue sequentially.
     */
    public void start() {
        t = new Thread(() -> {
            while (!stop) {
                try {
                    dispatchQueue.take().run();
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        t.start();
    }

    /**
     * Sends a command to FG by puting it in dispatchQueue.
     * @param parameter to set.
     * @param value to set.
     */
    public void send(String parameter, String value) {
        try {
            dispatchQueue.put(() -> {
                writer.print("set /controls/flight/" + parameter + value + "\r\n");
                writer.flush();
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
