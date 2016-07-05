package com.coloredcow.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   public final static String EXTRA_MESSAGE="com.coloredcow.myapplication";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void loggedIn(View v){
        //intent for logged In page
        EditText editText=(EditText)findViewById(R.id.uid);
        EditText e=(EditText)findViewById(R.id.pass);
        String passw=e.getText().toString();
        String message=editText.getText().toString();
        /*validation will be done here for now just taking an example. Manager and the user will have
        same login page but different dashboard*/
          if(message.equals("vaibhav") && passw.equals("bobby") ) {
              //    method to call clent ui
              test2(message, passw);
          }
          else {
              //Method to call user ui
              test1(message, passw);
          }
    }
    public void test1(String u, String p){
        Intent intentAdmin = new Intent(this, AdminDashBoard.class);
        intentAdmin.putExtra(EXTRA_MESSAGE, u);
        startActivity(intentAdmin);

    }
    public void test2(String u,String p)
    {   EditText editText=(EditText)findViewById(R.id.uid);
        EditText e=(EditText)findViewById(R.id.pass);
        String message=editText.getText().toString();
        Intent intentManager = new Intent(this, DashBoard.class);

        intentManager.putExtra(EXTRA_MESSAGE, message);
        startActivity(intentManager);
    }
}
