package com.example.androidappremotecontroljoystick.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
    private Button connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        host = findViewById(R.id.host);
        port = findViewById(R.id.port);
        connect = findViewById(R.id.connect);

        connect.setOnClickListener(v -> {
            String inputHost = host.getText().toString();
            String inputPort = port.getText().toString();
            if (inputHost.isEmpty() || inputPort.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                vm = new ViewModel(new FGModel());
                vm.connect(inputHost, Integer.parseInt(inputPort));
            }
        });
    }
}