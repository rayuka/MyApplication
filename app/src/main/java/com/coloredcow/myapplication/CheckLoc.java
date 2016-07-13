package com.coloredcow.myapplication;

import android.content.Context;
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
 * Created by asd on 7/13/2016.
 */
public class CheckLoc extends AsyncTask<String, Void, String>{
    public AsyncResponse delegate = null;
    private Context context;
    String location;
    String query_result;

    public CheckLoc(Context context) {
        this.context = context;
    }

    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... arg0) {
        location = arg0[0];
        String link;
        String data;
        BufferedReader bufferedReader;
        String result;

        try {
            data = "?location=" + URLEncoder.encode(location, "UTF-8");

            link = "http://192.168.1.20/piicus/checkloc.php" + data;
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
                   // Toast.makeText(context, " Login s", Toast.LENGTH_SHORT).show();

                }
                else if (query_result.equals("FAILURE")) {
                    Toast.makeText(context, "failure in accessing data", Toast.LENGTH_SHORT).show();

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
