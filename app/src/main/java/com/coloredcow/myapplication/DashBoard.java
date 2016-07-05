package com.coloredcow.myapplication;

import android.content.Intent;
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


        Intent intent=getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
       // Log.d("mylog","welcome,"+message);
       //if(message!="admin")
        Toast.makeText(this,"welcome "+message,Toast.LENGTH_SHORT ).show();
        TextView textView=new TextView(this);
        textView.setTextSize(40);
        textView.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        textView.setText(message);
        RelativeLayout layout=(RelativeLayout) findViewById(R.id.content);
        layout.addView(textView);
       // Log.d("MYLOG","welcome,"+message);
     //   else
       //    Toast.makeText(this,"user "+message+" not registered ",Toast.LENGTH_SHORT ).show();
        Button  b;
        b=(Button)findViewById(R.id.Send);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashBoard.this, AdminDashBoard.class));
            }
        });
    }

}
