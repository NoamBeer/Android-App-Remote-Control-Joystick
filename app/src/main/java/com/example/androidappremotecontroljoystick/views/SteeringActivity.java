package com.example.androidappremotecontroljoystick.views;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidappremotecontroljoystick.R;
import com.example.androidappremotecontroljoystick.viewModel.ViewModel;

import android.os.Bundle;

public class SteeringActivity extends AppCompatActivity {
    ViewModel vm;
    Joystick joystick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steering);

//        vm = (ViewModel) getIntent().getParcelableExtra("VM");
//        vm.setVMrudder(-1);
    }
}