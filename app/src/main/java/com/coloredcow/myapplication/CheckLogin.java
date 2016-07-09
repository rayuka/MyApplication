package com.coloredcow.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class CheckLogin extends AppCompatActivity  {
     Boolean my_first_time=true;
    //ValidateLogin v= new ValidateLogin(this);
    SharedPreferences settings;
  public final String PREFS_NAME = "MyPrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_login);
        settings = getPreferences(Context.MODE_PRIVATE);

        if (settings.getBoolean("my_first_time", true)) {
            settings.edit().putBoolean("my_first_time", false).commit();
            Intent intentAdmin = new Intent(this, Login.class);
            startActivity(intentAdmin);
            Log.d("Comments", "First time");
        }
        else {

            //     if(validate(u,p)){
           Intent intentA = new Intent(this,DashBoard.class);// change trial to dashboard
           startActivity(intentA);

            //   }
        }

    }
/*
    @Override
    public void processFinish(String output) {
        if(settings.getBoolean("my_first_time", true))
        {

        }

    }*/
}
