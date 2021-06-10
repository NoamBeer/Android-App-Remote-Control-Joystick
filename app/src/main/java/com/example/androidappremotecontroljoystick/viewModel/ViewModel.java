package com.example.androidappremotecontroljoystick.viewModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.androidappremotecontroljoystick.model.FGModel;

public class ViewModel extends androidx.lifecycle.ViewModel {
    private FGModel fgModel;

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

    public void connect(String host, int port) {
        fgModel.connect(host, port);
    }

    public void start() {
        fgModel.start();
    }

    public void disconnect() {
        fgModel.disconnect();
    }


}
