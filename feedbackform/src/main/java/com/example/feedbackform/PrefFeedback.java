package com.example.feedbackform;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PrefFeedback extends AppCompatActivity {
    Button button;
    TextView textView, uses, friends;
    EditText editText;
    CheckBox clothes, homeDecor, appliances, others;
    Switch switch1;
    String formResult = "";
    StringBuilder result, switchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pref_feedback);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.textView);
        uses = findViewById(R.id.uses);
        friends = findViewById(R.id.friends);

        uses.setText("What do you use Ebay for?");
        friends.setText("Would you recommend our website to a friends?");
        clothes = findViewById(R.id.clothes);
        homeDecor = findViewById(R.id.homeDecor);
        appliances = findViewById(R.id.appliances);
        others = findViewById(R.id.others);
        switch1 = findViewById(R.id.switch1);
        result = new StringBuilder();
        switchResult = new StringBuilder();


        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    switchResult.append("Will recommend to a friend");
                } else
                    switchResult.append("Will not recommend to a friend");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result.append("Uses Ebay for: ");
                if (clothes.isChecked())
                    result.append("Clothes ");
                else if (homeDecor.isChecked())
                    result.append("Home Decor ");
                else if (appliances.isChecked())
                    result.append("Appliances ");
                else if (others.isChecked())
                    result.append("Other ");

                result.append(switchResult);

                formResult = String.valueOf(result);


                SharedPreferences shrd = getSharedPreferences("demo", MODE_PRIVATE);
                SharedPreferences.Editor editor = shrd.edit();

                editor.putString("str1", formResult);
                editor.apply();

                textView.setText(formResult);

            }
        });

        // Get the value of shared preference back
        SharedPreferences getShared = getSharedPreferences("demo", MODE_PRIVATE);
        String value1 = getShared.getString("str1", ".");

        textView.setText(value1);


    }
}