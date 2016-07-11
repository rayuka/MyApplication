package com.coloredcow.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
                startActivity(new Intent(AdminDashBoard.this,Login.class));
            }
        });

    }
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
}
