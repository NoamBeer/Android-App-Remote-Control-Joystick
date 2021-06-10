package com.example.androidappremotecontroljoystick.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidappremotecontroljoystick.R;
import com.example.androidappremotecontroljoystick.model.FGModel;
import com.example.androidappremotecontroljoystick.viewModel.ViewModel;

public class MainActivity extends AppCompatActivity {
    private ViewModel vm;
    private EditText host;
    private EditText port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        host = findViewById(R.id.host);
        port = findViewById(R.id.port);
        Button connect = findViewById(R.id.connect);

//      create a new ViewModel
        ViewModel.createViewModel();
        vm = ViewModel.getViewModel();

//      define behavior upon clicking "connect"
        connect.setOnClickListener(v -> {
            String inputHost = host.getText().toString();
            String inputPort = port.getText().toString();
//          validate fields
            if (inputHost.isEmpty() || inputPort.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                vm.connect(inputHost, Integer.parseInt(inputPort));
//              validate socket connection
                if (!vm.isConnected()) {
                    Toast.makeText(this, "Connection was not established, please wait and then reconnect", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(this, SteeringActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}