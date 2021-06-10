package com.example.androidappremotecontroljoystick.viewModel;

import com.example.androidappremotecontroljoystick.model.FGModel;

public class ViewModel extends androidx.lifecycle.ViewModel {
    private FGModel fgModel;
    private static ViewModel viewModel;

    /**
     * Private constructor of ViewModel.
     *
     * @param fgModel - Model
     */
    private ViewModel(FGModel fgModel) {
        this.fgModel = fgModel;
    }

    /**
     * ViewModel getter, returns an instance of ViewModel in case it was already created.
     *
     * @return Instance of ViewModel.
     */
    public static ViewModel getViewModel() {
        if (viewModel == null) {
            try {
                throw new Exception("viewModel was not created");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return viewModel;
    }

    /**
     * ViewModel creator, creates a ViewModel in case it wasn't created before.
     */
    public static void createViewModel() {
        if (viewModel != null) {
            try {
                throw new Exception("viewModel was already created");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        viewModel = new ViewModel(FGModel.getFgModel());
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
