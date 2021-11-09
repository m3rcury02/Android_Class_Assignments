package com.example.loginservice;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView msg = findViewById(R.id.msg);
        if(getIntent().hasExtra("name")){
            String name = getIntent().getStringExtra("name");
            msg.setText("Welcome, "+ name);
        }
        String login = getIntent().getStringExtra("login");
        TextView loginMsg = findViewById(R.id.loginMsg);
        loginMsg.setText(login);
    }
}
