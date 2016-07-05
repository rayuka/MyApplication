package com.coloredcow.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AdminDashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash_board);
// add branch activity
        Button b;
        b=(Button)findViewById(R.id.addBranch);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashBoard.this, UserRegistration.class));
            }
        });
     //   Intent intent=getIntent();
        // String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
       /* if(message.length()==1)
        {}
        else
        Toast.makeText(this,"welcome "+message,Toast.LENGTH_SHORT ).show();

        TextView textView=new TextView(this);
        textView.setTextSize(40);
        textView.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        textView.setText(message);
        RelativeLayout layout=(RelativeLayout) findViewById(R.id.content);
        layout.addView(textView);*/

    }
    public void createButton()
    {//programmically creating button..(still working on it)
        LinearLayout myLayout =(LinearLayout) findViewById(R.id.pannelAdmin);

        Button myButton = new Button(AdminDashBoard.this);
        myButton.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));

        myLayout.addView(myButton);
    }
}
