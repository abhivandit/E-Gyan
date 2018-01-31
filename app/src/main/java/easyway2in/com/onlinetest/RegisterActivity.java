package easyway2in.com.onlinetest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static easyway2in.com.onlinetest.R.id.bRegister;
import static easyway2in.com.onlinetest.R.id.etAge;
import static easyway2in.com.onlinetest.R.id.etFirstName;
import static easyway2in.com.onlinetest.R.id.etLastName;
import static easyway2in.com.onlinetest.R.id.etPassword;
import static easyway2in.com.onlinetest.R.id.etUsername;
import static easyway2in.com.onlinetest.R.styleable.AlertDialog;

public class RegisterActivity extends AppCompatActivity implements OnTaskCompleted{
    private EditText etFirstName ,etLastName,etAge,etEmail,etUsername,etPassword;
    private String firstname, lastname, age, email, username, password;
    Button bRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etFirstName=(EditText) findViewById(R.id.etFirstName);
        etLastName=(EditText)findViewById(R.id.etLastName);
        etAge=(EditText)findViewById(R.id.etAge);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etUsername=(EditText)findViewById(R.id.etUsername);
        etPassword=(EditText)findViewById(R.id.etPassword);
        bRegister=(Button)findViewById(R.id.bRegister);
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
                Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent1);
            }
        });
    }
    public void register(){
        initialize();

        if(!validate()){
            Toast.makeText(this,"Registration has failed",Toast.LENGTH_SHORT).show();
        }
        else{
            onRegisterSuccess();
        }
    }
    public  void onRegisterSuccess(){
        String type="Register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this,this);
        backgroundWorker.execute(type,firstname,lastname,age,email,username,password);
    }
    public boolean validate(){
        boolean valid=true;
        if (firstname.isEmpty()) {
            etFirstName.setError("Can't be empty");
            valid = false;
        } else {
            etFirstName.setError(null);

        }
        if (lastname.isEmpty()) {
            etLastName.setError("Can't be empty");
            valid = false;
        } else {
            etLastName.setError(null);

        }
        if (age.isEmpty()) {
            etAge.setError("Can't be empty");
            valid = false;
        } else {
            etAge.setError(null);

        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter valid email address ");
            valid = false;
        } else {
            etEmail.setError(null);

        }
        if (username.isEmpty()) {
            etUsername.setError("Can't be empty");
            valid = false;
        } else {
            etUsername.setError(null);

        }
        if (password.isEmpty() || password.length() < 4 || password.length() > 15) {
            etPassword.setError("Please enter a valid password between 4 and 15 alphanumeric characters.");
            valid = false;
        } else {
            etPassword.setError(null);
        }
        return valid;
    }
    public void initialize(){
        firstname=etFirstName.getText().toString().trim();
        lastname=etLastName.getText().toString().trim();
        age=etAge.getText().toString().trim();
        email=etEmail.getText().toString().trim();
        username=etUsername.getText().toString().trim();
        password=etPassword.getText().toString().trim();
    }

    @Override
    public void onBackPressed() {
        //  super.onBackPressed();
        Intent intent1 = new Intent(RegisterActivity.this,
                LoginActivity.class);
        //iintent.put

        startActivity(intent1);

        finish();
    }
    @Override
    public void onTaskCompleted(String responseJson) {
    }

/*        builder = new AlertDialog.Builder(RegisterActivity.this);
        bRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String firstname = etFirstName.getText().toString();
                final String lastname = etLastName.getText().toString();
                final int age = Integer.parseInt(etAge.getText().toString());
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                if (firstname.equals("") || username.equals("") || password.equals("")) {
                    builder.setTitle("Something went wrong...");
                    builder.setMessage("Please fill all the fields.");
                    displayAlert("Input error");
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_REQUEST_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                String code = jsonObject.getString("code");
                                String message = jsonObject.getString("message");
                                builder.setTitle("Server Response");
                                builder.setMessage(message);
                                displayAlert(code);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }) {
                        protected Map<String, String> getparams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("firstname", firstname);
                            params.put("lastname", lastname);
                            params.put("age", age + "");
                            params.put("username", username);
                            params.put("password", password);
                            return params;
                        }
                    };
                    MySingleton.getInstance(RegisterActivity.this).addToRequestQueue(stringRequest);
                } */
         /*   }

                /*Response.Listener<String> responseListener = new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Registration failed.")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        }
                        catch(JSONException e){
                            e.printStackTrace();
                        }*/
    /*    });
    }

    public void displayAlert(final String code) {
                    /*RegisterRequest registerRequest = new RegisterRequest(firstname, lastname, age, username, password, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerRequest);*/
    /*    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*if (code.equals("Input error")) {

                }*/
 /*           }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
*/

}