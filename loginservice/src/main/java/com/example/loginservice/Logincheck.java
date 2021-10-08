package com.example.loginservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Logincheck extends Service {
    public Logincheck() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        String username = intent.getExtras().getString("username");
        String password = intent.getExtras().getString("password");

        if(username.equals("m3rcury02") && password.equals("gunal1501")){
            Intent next = new Intent(getApplicationContext(), Home.class);
            next.putExtra("name", username);
            next.putExtra("login", "Successfully Logged in!");
            next.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(next);
        }
        else {
            Intent next = new Intent(getApplicationContext(), Home.class);
            next.putExtra("login", "Credentials mismatch! Please try again...");
            next.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(next);
        }


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}