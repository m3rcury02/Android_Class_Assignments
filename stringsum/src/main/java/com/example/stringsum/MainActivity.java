package com.example.stringsum;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void add(View view) {
        EditText a = findViewById(R.id.a);
        EditText b = findViewById(R.id.b);
        TextView sum = findViewById(R.id.result);
        int first = Integer.parseInt(a.getText().toString());
        int second = Integer.parseInt(b.getText().toString());
        sum.setText("Sum of values: " + (first + second));
    }
}