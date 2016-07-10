package com.coloredcow.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class CheckLogin extends AppCompatActivity  implements AsyncResponse {
   static Boolean my_first_time=true;
    String userName,passWord;
    public final static String EXTRA_MESSAGE="com.coloredcow.myapplication";
    ValidateLogin _login=new ValidateLogin(this);
   //SharedPreferences mPrefs = getSharedPreferences("IDvalue",Context.MODE_PRIVATE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_login);
        SharedPreferences mPrefs = getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        userName=mPrefs.getString("_user_name","");
        passWord=mPrefs.getString("_pass_","");
        my_first_time=mPrefs.getBoolean("my_first_time",true);
        if (my_first_time) {
            Intent intentAdmin = new Intent(this, Login.class);
            startActivity(intentAdmin);
            Log.d("Comments", "First time");
        }
        else {
            _login.delegate=CheckLogin.this;
            _login.execute(userName,passWord);
        }

    }

    @Override
    public void processFinish(String output) {
        if(output.equals("SUCCESS"))
        {
            Intent intentAdmin = new Intent(CheckLogin.this,DashBoard.class);
            intentAdmin.putExtra(EXTRA_MESSAGE,userName);
            startActivity(intentAdmin);
        }
        else if(output.equals("SUCCESSFUL"))
        {
            Intent intentAdmin = new Intent(CheckLogin.this,AdminDashBoard.class);
            intentAdmin.putExtra(EXTRA_MESSAGE,userName);
            startActivity(intentAdmin);
        }
        else
            Toast.makeText(CheckLogin.this,"Wrong credentials",Toast.LENGTH_LONG).show();
    }
}
