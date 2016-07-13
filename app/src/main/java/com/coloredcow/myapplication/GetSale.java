package com.coloredcow.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by asd on 7/11/2016.
 */
public class GetSale extends AsyncTask<String, Void, String> {
    public Transporter del = null;
    private Context context;
    String username;
    String password;
    String name;
    String location;
    String email;
    String mobile;
    JSONArray query_result;
    public GetSale(Context context) {
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
        String result="";

        try {
            data = "?location=" + URLEncoder.encode(location, "UTF-8");;

            link = "http://192.168.1.20/piicus/viewSales.php" + data;
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            String line="";
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            //result = bufferedReader.readLine();
      //Toast.makeText(context,result,Toast.LENGTH_LONG);

           while ((line=bufferedReader.readLine())!=null)
              result+=line;
            return result;
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
//        Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        String jsonStr = result;

        //String g;
        //Toast.makeText(context," hello ::exception"+jsonStr,Toast.LENGTH_LONG).show();
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                query_result = jsonObj.getJSONArray("result");
// /                Toast.makeText(context,"exception"+query_result,Toast.LENGTH_LONG).show();
                if (query_result.equals("SUCCESS")) {
            //        Toast.makeText(context, "Data inserted successfully. Signup successfull.", Toast.LENGTH_SHORT).show();

                } else if (query_result.equals("FAILURE")) {
          //          Toast.makeText(context, "Data could not be inserted. Signup failed.", Toast.LENGTH_SHORT).show();
                } else {
        //            Toast.makeText(context, "Couldn't connect to remote database.", Toast.LENGTH_SHORT).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
      //          Toast.makeText(context, "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
    //            Toast.makeText(context,"exception"+e.getMessage(),Toast.LENGTH_LONG).show();
            }
        } else {
  //          Toast.makeText(context, "Couldn't get any JSON data.", Toast.LENGTH_SHORT).show();
        }
        del.TransferArray(query_result);
    }
}
