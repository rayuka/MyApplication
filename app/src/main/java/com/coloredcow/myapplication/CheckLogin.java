package com.coloredcow.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class CheckLogin extends AppCompatActivity {
    static Boolean my_first_time=true;
    //  Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_login);


        final String PREFS_NAME = "MyPrefsFile";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time, do something

            Intent intentAdmin = new Intent(this, Login.class);
            startActivity(intentAdmin);

            Log.d("Comments", "First time");
            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit();
        } else {
            //     if(validate(u,p)){
           Intent intentA = new Intent(this,MainActivity.class);// change trial to dashboard
           startActivity(intentA);

            //   }
        }
/*
        Intent intentAdmin = new Intent(this, MainActivity.class);
        startActivity(intentAdmin);*/
    }
}
