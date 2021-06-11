package com.example.androidappremotecontroljoystick.views;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidappremotecontroljoystick.R;
import com.example.androidappremotecontroljoystick.viewModel.ViewModel;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

public class SteeringActivity extends AppCompatActivity implements Joystick.JoystickListener {
    ViewModel vm;
    Joystick joystick;
    SeekBar rudder;
    SeekBar throttle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        joystick = new Joystick(this);
        setContentView(R.layout.activity_steering);
        rudder = findViewById(R.id.Rudder);
        throttle = findViewById(R.id.Throttle);
        vm = ViewModel.getViewModel();
        vm.start();

        rudder.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float rudderVal = ((float) progress - 50) / 50;
                vm.setVMrudder(rudderVal);
                Log.d(null, "Rudder: " + rudderVal);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        throttle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float throttleVal = (float) progress  / 100;
                vm.setVMthrottle(throttleVal);
                Log.d(null, "Throttle: " + throttleVal);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onJoystickMoved(float xPos, float yPos) {
        vm.setVMelevator(-yPos);
        vm.setVMaileron(-xPos);
        Log.d(null, "X: " + xPos + "% Y: " + yPos + "%");
    }
}