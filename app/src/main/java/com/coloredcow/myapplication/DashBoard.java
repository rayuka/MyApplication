package com.coloredcow.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DashBoard extends AppCompatActivity implements AsyncResponse {
    UpdateSale _sale= new UpdateSale(this);
    String username,sale;

//    Context context;
    public final static String EXTRA_MESSAGE="com.coloredcow.myapplication";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

//recieving intent from main
        Intent intent = getIntent();
        String message = intent.getStringExtra(Login.EXTRA_MESSAGE);
        username=message;
     //   Toast.makeText(DashBoard.this,message, Toast.LENGTH_SHORT).show();
        //if(message!="admin")
        Toast.makeText(this, "welcome " + message, Toast.LENGTH_SHORT).show();
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        textView.setText(message);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.content);
        layout.addView(textView);
        Button c=(Button)findViewById(R.id.logout);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mPrefs = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mPrefs.edit();
                editor.putString("_user_name", "");
                editor.putString("_pass_", "");
                editor.putBoolean("my_first_time",true);
                editor.commit();
                startActivity(new Intent(DashBoard.this,Login.class));
            }
        });
    }

    public void saleSend(View v)
    {
        EditText s=(EditText)findViewById(R.id.sale);
        sale=s.getText().toString();
        _sale.delegate=this;
        _sale.execute(username,sale);
    }

    @Override
    public void processFinish(String output) {
        if(output.equals("SUCCESS")) {
            Toast.makeText(DashBoard.this,"sales sent successfully", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(DashBoard.this,"sales sending failed", Toast.LENGTH_SHORT).show();
        }
        Intent intentAdmin = new Intent(DashBoard.this,DashBoard.class);
        intentAdmin.putExtra(EXTRA_MESSAGE, username);
        startActivity(intentAdmin);
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
            }

    }

