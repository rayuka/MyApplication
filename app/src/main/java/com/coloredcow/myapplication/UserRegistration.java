package com.coloredcow.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class UserRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        LinearLayout myLayout = (LinearLayout) findViewById(R.id.pannelAdmin);

        Button b;
        b=(Button)findViewById(R.id.reg2);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AdminDashBoard a=new AdminDashBoard();
                a.createButton();
                startActivity(new Intent(UserRegistration.this,AdminDashBoard.class));
            }
        });
    }
}
