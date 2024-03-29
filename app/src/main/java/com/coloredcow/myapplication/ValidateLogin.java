package com.coloredcow.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by asd on 7/8/2016.
 */
public class ValidateLogin extends AsyncTask<String, Void, String>
{  public AsyncResponse delegate = null;
    private Context context;
    String username, password;
    String query_result;

    public ValidateLogin(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... arg0) {
        username = arg0[0];
        password = arg0[1];
        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        try {
            data = "?username=" + URLEncoder.encode(username, "UTF-8");
            data += "&password=" + URLEncoder.encode(password, "UTF-8");

            link = "http://192.168.1.20/piicus/login.php" + data;
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
            //Toast.makeText(context,"hey there"+result,Toast.LENGTH_LONG);
            return result;
            //return data;
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
    //    Toast.makeText(context,username+"hey"+password,Toast.LENGTH_LONG).show();
        String jsonStr = result;
      //  Toast.makeText(context," hello ::exception"+jsonStr,Toast.LENGTH_LONG).show();
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                query_result = jsonObj.getString("query_result");
              //  Toast.makeText(context,"exception"+query_result,Toast.LENGTH_LONG).show();
                if (query_result.equals("SUCCESS")||query_result.equals("SUCCESSFUL")) {
                    Toast.makeText(context, " Login successful.", Toast.LENGTH_SHORT).show();

                }
                else if (query_result.equals("FAILURE")) {
                    Toast.makeText(context, "Login failed.", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "Couldn't connect to remote database.", Toast.LENGTH_SHORT).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
               // Toast.makeText(context,"exception"+e.getMessage(),Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(context, "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
        }
            delegate.processFinish(query_result);
    }

}