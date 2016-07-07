package com.coloredcow.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

//recieving intent from main
        Intent intent = getIntent();
        String message = intent.getStringExtra(Login.EXTRA_MESSAGE);
        // Log.d("mylog","welcome,"+message);
        //if(message!="admin")
        Toast.makeText(this, "welcome " + message, Toast.LENGTH_SHORT).show();
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        textView.setText(message);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.content);
        layout.addView(textView);
        // Log.d("MYLOG","welcome,"+message);
        //   else
        //    Toast.makeText(this,"user "+message+" not registered ",Toast.LENGTH_SHORT ).show();
        //calling to  new activity
        Button b;
        b = (Button) findViewById(R.id.Send);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this, AdminDashBoard.class));
            }
        });

        Button c=(Button)findViewById(R.id.logout);
        String PREFS_NAME = "MyPrefsFile";
        c.setOnClickListener(new View.OnClickListener() {

            //final String PREFS_NAME = "MyPrefsFile";
            @Override
            public void onClick(View v) {
              //  SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
              //  settings.edit().putBoolean("my_first_time", false).commit();
                startActivity(new Intent(DashBoard.this,Login.class));
            }
        });
    }
}
