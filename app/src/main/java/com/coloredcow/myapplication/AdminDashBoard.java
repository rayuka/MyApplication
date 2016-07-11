package com.coloredcow.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdminDashBoard extends AppCompatActivity implements Transporter {
    public final static String EXTRA_MESSAGE = "com.coloredcow.myapplication";
    public static String result = null;
    GetSale p = new GetSale(this);
    EditText local;
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash_board);
// add branch activity
        Button c = (Button) findViewById(R.id.logout);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mPrefs = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mPrefs.edit();
                editor.putString("_user_name", "");
                editor.putString("_pass_", "");
                editor.putBoolean("my_first_time", true);
                editor.commit();
                startActivity(new Intent(AdminDashBoard.this, Login.class));
            }
        });

    }

    public void signup(View v) {
        local = (EditText) findViewById(R.id.city);
        location = local.getText().toString();
        if (location.equals(""))
            startActivity(new Intent(AdminDashBoard.this, AdminDashBoard.class));
        else {
            p.delegate = this;
            p.execute(location);

        }
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void TransferArray(JSONArray query_result) {


    setContentView(R.layout.list_item);

    ListView lv = (ListView) findViewById(R.id.listView_sale);
    List<String> your_array_list = new ArrayList<String>();

    try {
        for (int j = 0; j < query_result.length(); j++) {
            JSONObject obj1 = query_result.getJSONObject(j);
            String firstName = obj1.getString("username");
            //Toast.makeText(this,firstName,Toast.LENGTH_LONG).show();
            String sales = obj1.getString("sale");
            //Toast.makeText(this,sales,Toast.LENGTH_LONG).show();
            String dates = obj1.getString("date");
            your_array_list.add(firstName);
            your_array_list.add(sales);
            your_array_list.add(dates);
            your_array_list.add("  ");

        }
    } catch (JSONException e) {
        e.printStackTrace();
        Toast.makeText(this, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "exception" + e.getMessage(), Toast.LENGTH_LONG).show();
    }

    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, your_array_list);

    lv.setAdapter(arrayAdapter);
    Button x = (Button) findViewById(R.id.goBack);
    x.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(AdminDashBoard.this, AdminDashBoard.class));

        }
    });

        }
}