package easyway2in.com.onlinetest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static easyway2in.com.onlinetest.R.id.etPassword;
import static easyway2in.com.onlinetest.R.id.etUsername;

public class LoginActivity extends AppCompatActivity implements OnTaskCompleted {
    String password ,username;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final Button bLogin = (Button) findViewById(R.id.bLogin);

        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);


        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                if (username.equals("") || username.length() == 0) {
                    etUsername.setError("Username can't be blank.");
                } else if (password.equals("") || password.length() == 0) {
                    etPassword.setError("Password can't be blank.");
                } else {
                    saveLoginDetails(username, password);
                    //startActivity(new Intent(LoginActivity.this, LoginSuccess.class));
                    // finish();
                    BackgroundWorker();
                }
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
    }

    private void saveLoginDetails(String username, String password) {
        new PrefManager(this).saveLoginDetails(username, password);
    }

    public void BackgroundWorker(){

        String type = "Login";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this, this);
        backgroundWorker.execute(type, username, password);
    }

    @Override
    public void onTaskCompleted(String responseJson) {

    }
}