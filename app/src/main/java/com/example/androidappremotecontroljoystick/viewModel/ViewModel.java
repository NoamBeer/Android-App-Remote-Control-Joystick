package com.example.androidappremotecontroljoystick.viewModel;

import com.example.androidappremotecontroljoystick.model.FGModel;

public class ViewModel extends androidx.lifecycle.ViewModel {
    private FGModel fgModel;
    private double VMaileron;
    private double VMelevator;
    private double VMrudder;
    private double VMthrottle;


    public ViewModel(FGModel fgModel) {
        this.fgModel = fgModel;
    }

    public void setVMaileron(double VMaileron) {
        fgModel.setAileron(VMaileron);
    }

    public void setVMelevator(double VMelevator) {
        fgModel.setElevator(VMelevator);
    }

    public void setVMrudder(double VMrudder) {
        fgModel.setRudder(VMrudder);
    }

    public void setVMthrottle(double VMthrottle) {
        fgModel.setThrottle(VMthrottle);
    }

    //    TODO
    public void connect(String host, int port) {
        fgModel.connect(host, port);
    }
}
