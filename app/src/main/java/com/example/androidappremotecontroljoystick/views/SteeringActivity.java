package com.example.androidappremotecontroljoystick.views;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidappremotecontroljoystick.R;
import com.example.androidappremotecontroljoystick.viewModel.ViewModel;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

public class SteeringActivity extends AppCompatActivity implements Joystick.JoystickListener {
    ViewModel vm;
    Joystick joystick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        joystick = new Joystick(this);
        setContentView(R.layout.activity_steering);
        vm = ViewModel.getViewModel();
        vm.start();
    }

    @Override
    public void onJoystickMoved(float xPos, float yPos) {
        vm.setVMelevator(-yPos);
        vm.setVMaileron(-xPos);
        Log.d(null, "X: " + xPos + "% Y: " + yPos + "%");
    }
}