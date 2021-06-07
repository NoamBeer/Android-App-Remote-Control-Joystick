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

    public void setAileron(double aileron) {
        send("set /controls/flight/aileron " + aileron + "\r\n");
    }

    public void setElevator(double elevator) {
        send("set /controls/flight/elevator " + elevator + "\r\n");
    }

    public void setRudder(double rudder) {
        send("set /controls/flight/elevator " + rudder + "\r\n");
    }

    public void setThrottle(double throttle) {
        send("set /controls/flight/elevator " + throttle + "\r\n");
    }

    public void connect(String host, int port) {
        try {
            fgConnection = new Socket(host, port);
            writer = new PrintWriter(fgConnection.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        stop = true;
        t.interrupt();
    }

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

    public void send(String command) {
        try {
            dispatchQueue.put(() -> {
                writer.print(command);
                writer.flush();
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
