package com.coloredcow.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
EditText username, password;
    String u,p;
    public final static String EXTRA_MESSAGE="com.coloredcow.myapplication";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button b;
        b=(Button)findViewById(R.id.registeration);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                username=(EditText)findViewById(R.id.uname);
                password=(EditText)findViewById(R.id.pass);
                startActivity(new Intent(Login.this, MainActivity.class));
            }
        });
      /*  Button c;
        c=(Button)findViewById(R.id.login);
        c.setOnClickListener(new View.OnClickListener(){
         /*   @Override
            public void onClick(View v) {
                if((Validate(u)).equals(1))

              {
                    //admin
                    Intent intentAdmin = new Intent(Login.this, AdminDashBoard.class);
                    intentAdmin.putExtra(EXTRA_MESSAGE, u);
                    startActivity(intentAdmin);

                }
                else if(Validate(u,p).equals(2)){
               //manager
                    Intent intentManager = new Intent(Login.this, AdminDashBoard.class);
                    intentManager.putExtra(EXTRA_MESSAGE, u);
                    startActivity(intentManager);
                }
                else {
                    Toast.makeText(Login.this,"Wrong credentials",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Login.this, Login.class));
                }

            }
        });*/

    }
}
