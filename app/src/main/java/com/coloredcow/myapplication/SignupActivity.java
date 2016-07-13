package com.coloredcow.myapplication;
/**
 * Created by asd on 7/6/2016.
 */

import android.content.Context;
import android.widget.Toast;
import  android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

 class SignupActivity extends AsyncTask<String, Void, String> {
     public AsyncResponse delegate = null;
    private Context context;
    String username,password,name,location,email,mobile,query_result;
    public SignupActivity(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {
    }
    @Override
    protected String doInBackground(String... arg0) {
        username = arg0[0];
        password = arg0[1];
        name=arg0[2];
        location=arg0[3];
        email=arg0[4];
        mobile=arg0[5];
        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        try {
            data = "?username=" + URLEncoder.encode(username, "UTF-8");
            data += "&password=" + URLEncoder.encode(password, "UTF-8");
            data += "&name=" + URLEncoder.encode(name, "UTF-8");
            data += "&location=" + URLEncoder.encode(location, "UTF-8");
            data += "&email=" + URLEncoder.encode(email, "UTF-8");
            data += "&mobile=" + URLEncoder.encode(mobile, "UTF-8");

            link = "http://192.168.1.20/piicus/signup.php" + data;
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
            return result;
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context,username+"hey"+password,Toast.LENGTH_LONG).show();
        String jsonStr = result;
        Toast.makeText(context," hello ::exception"+jsonStr,Toast.LENGTH_LONG).show();
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                query_result = jsonObj.getString("query_result");
                Toast.makeText(context,"exception"+query_result,Toast.LENGTH_LONG).show();
                if (query_result.equals("SUCCESS")) {
                    Toast.makeText(context, "Data inserted successfully. Signup successfull.", Toast.LENGTH_SHORT).show();

                } else if (query_result.equals("FAILURE")) {
                    Toast.makeText(context, "Data could not be inserted. Signup failed.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Couldn't connect to remote database.", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
                Toast.makeText(context,"exception"+e.getMessage(),Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(context, "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
        }
        delegate.processFinish(query_result);
    }
}