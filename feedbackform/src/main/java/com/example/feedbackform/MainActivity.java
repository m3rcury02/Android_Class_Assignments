package com.example.feedbackform;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    CheckBox web, deli, supp;
    StringBuilder toastMsg = new StringBuilder();
    Calendar cal = Calendar.getInstance();
    RatingBar rateBar;
    String radTxt, switchToast;
    TextView PrefText;
    String formResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButtonClick();
    }

    public void addListenerOnButtonClick() {
        web = findViewById(R.id.web);
       deli=findViewById(R.id.deli);
        supp = findViewById(R.id.support);
        PrefText = findViewById(R.id.PrefTxt);
       EditText name =findViewById(R.id.nametxt);
       Button submit=findViewById(R.id.submit);
       //Date
        TextView dateText=findViewById(R.id.datetxt);
        DatePicker date=(DatePicker)findViewById(R.id.dobcal);
        date.init(2000,01,01,
        new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        cal.set(year,monthOfYear,dayOfMonth);
                        dateText.setText(date.getDayOfMonth()+"/"+(date.getMonth()+1)+"/"+date.getYear());
                        }
                });
        //Rating
        rateBar=(RatingBar)findViewById(R.id.ratingbar);
        rateBar.setRating(Float.parseFloat("2.0"));
        //RadioButton
        RadioGroup freq=(RadioGroup)findViewById(R.id.usageTime);
        freq.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId !=-1){
                    RadioButton rb=(RadioButton)findViewById(checkedId);
                    if(rb != null) {
                        radTxt=rb.getText().toString();
                    }
                }
            }
        });
        //Switch button
        Switch switchB=(Switch)findViewById(R.id.news);
        switchToast=("Subscribed");
        switchB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    switchToast=("Subscribed");
                }
                else
                {
                    switchToast=("Not Subscribed");
                }
                Log.v("Switch State=",""+isChecked);
            }
        });
       //Toast after submit
       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               toastMsg.append("\nName :"+name.getText());
               toastMsg.append("\nDate of Birth :"+date.getDayOfMonth()+"/"+(date.getMonth()+1)+"/"+date.getYear());
               if(web.isChecked()|| deli.isChecked()||supp.isChecked())
                   toastMsg.append("\nSatisfied with: ");
               else toastMsg.append("\nSatisfied with nothing");
               if (web.isChecked())
                   toastMsg.append("\nWebsite");
               if (deli.isChecked())
                   toastMsg.append("\nDelivery");
               if (supp.isChecked())
                   toastMsg.append("\nCustomer support");
               toastMsg.append("\nUsage of Service:" + radTxt);
               toastMsg.append("\nRating: " + rateBar.getRating());
               toastMsg.append("\nNewsletter: " + switchToast);
               Toast.makeText(getApplicationContext(), toastMsg.toString(), Toast.LENGTH_LONG).show();

               SharedPreferences shared = getSharedPreferences("demo", MODE_PRIVATE);
               SharedPreferences.Editor editor = shared.edit();
               formResult = String.valueOf(toastMsg);
               editor.putString("str1", formResult);
               editor.apply();
               PrefText.setText(formResult);
           }
       });
        SharedPreferences getShared = getSharedPreferences("demo", MODE_PRIVATE);
        String value1 = getShared.getString("str1", ".");

        PrefText.setText(value1);
        toastMsg.setLength(0);
    }
}