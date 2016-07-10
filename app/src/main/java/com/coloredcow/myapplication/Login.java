package com.coloredcow.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements AsyncResponse {
  public EditText username, password;
  public  String u,p,result;
    public final static String EXTRA_MESSAGE="com.coloredcow.myapplication";
    ValidateLogin _login=new ValidateLogin(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button b;
        b=(Button)findViewById(R.id.registration);
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, MainActivity.class));
            }
        });
    }

    public void chooseDash(View v)
    {
        username=(EditText)findViewById(R.id.luname);
        password=(EditText)findViewById(R.id.lpass);
        u=username.getText().toString();
        p=password.getText().toString();
        _login.delegate=Login.this;
        _login.execute(u,p);
    }
    public void processFinish(String output) {
        result=output;
        if(result.equals("SUCCESS"))
        {
            SharedPreferences mPrefs = getSharedPreferences("userInfo",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putString("_user_name", u);
            editor.putString("_pass_", p);
            editor.putBoolean("my_first_time",false);
            editor.commit();
            Intent intentAdmin = new Intent(Login.this,DashBoard.class);
            intentAdmin.putExtra(EXTRA_MESSAGE, u);
            startActivity(intentAdmin);
        }
        else if(result.equals("SUCCESSFUL"))
        {
            SharedPreferences mPrefs = getSharedPreferences("userInfo",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.putString("_user_name", u);
            editor.putString("_pass_", p);
            editor.putBoolean("my_first_time",false);
            editor.commit();
            Intent intentAdmin = new Intent(Login.this,AdminDashBoard.class);
            intentAdmin.putExtra(EXTRA_MESSAGE, u);
            startActivity(intentAdmin);
        }

        else
            Toast.makeText(Login.this,"Wrong credentials",Toast.LENGTH_LONG).show();
    }
}
