 package com.coloredcow.myapplication;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import org.json.JSONArray;
        import org.json.JSONObject;

 public class MainActivity extends AppCompatActivity implements AsyncResponse {
    EditText username,password,email,name,cpass,location,mobile;
    public final static String EXTRA_MESSAGE="com.coloredcow.myapplication";
    public static String result=null;
     SignupActivity s= new SignupActivity(this);
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void signup(View v){
        username=(EditText)findViewById(R.id.uname);
        password=(EditText)findViewById(R.id.pass);
        email=(EditText)findViewById(R.id.Email);
        name=(EditText)findViewById(R.id.Name);
        location=(EditText)findViewById(R.id.location);
        mobile=(EditText)findViewById(R.id.phone);
        cpass=(EditText)findViewById(R.id.cpass);
        String _pass=password.getText().toString();
        String _uname=username.getText().toString();
        String _email=email.getText().toString();
        String _name=name.getText().toString();
        String _location=location.getText().toString();
        String _mobile=mobile.getText().toString();
        String _cpass=cpass.getText().toString();
        String []a=new String[10000];
        if(_pass.equals(_cpass)) {
            Toast.makeText(this, "Signing up...", Toast.LENGTH_SHORT).show();
            s.delegate=this;
            s.execute(_uname, _pass, _name, _location, _email, _mobile);

        }
        else{
            Intent i=new Intent(MainActivity.this,MainActivity.class);
            Toast.makeText(this,"password and confirm password don't match",Toast.LENGTH_SHORT).show();
            startActivity(i);
        }

    }

     @Override
     public void processFinish(String output) {
         result=output;
        if(result.equals("SUCCESS"))
            startActivity(new Intent(this, Login.class));
     }

/*if(validate(message,passw))
    {
    public void loggedIn(View v){
        //intent for logged In page
       //validation will be done here for now just taking an example. Manager and the user will have
        //same login page but different dashboard
       //   if(message.equals("vaibhav") && passw.equals("bobby") ) {
      //    method to call clent ui
              test2(message, passw);
          }
          else {
              //Method to admin ui
              test1(message, passw);
          }
    }
    public void test1(String u, String p){
        // creating intent for adminDashboard
        Intent intentAdmin = new Intent(this, AdminDashBoard.class);
        intentAdmin.putExtra(EXTRA_MESSAGE, u);
        startActivity(intentAdmin);

    }
    public void test2(String u,String p)
    {
        // intent for user dashboard
        Intent intentManager = new Intent(this, DashBoard.class);
        intentManager.putExtra(EXTRA_MESSAGE, u);
        startActivity(intentManager);
    }*/
}
