package com.example.androidappremotecontroljoystick.model;

public class FGModel {
    private double aileron;
    private double elevator;
    private double rudder;
    private double throttle;

    public void setAileron(double aileron) {
        this.aileron = aileron;
        //TODO: send to FG
    }

    public void setElevator(double elevator) {
        this.elevator = elevator;
        //TODO: send to FG
    }

    public void setRudder(double rudder) {
        this.rudder = rudder;
        //TODO: send to FG
    }

    public void setThrottle(double throttle) {
        this.throttle = throttle;
        //TODO: send to FG
    }

    //    TODO
    public void connect(String host, int port) {
    }

    //    TODO
    public void disconnect() {
    }

    //    TODO
    public void start() {
    }

    //    TODO
    public void send(String command) {
    }
}
