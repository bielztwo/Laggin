package com.example.laggin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity{
    EditText fillemail, fillpass;
    Button buttonlogin;
    TextView signup;
    Database data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fillemail = (EditText) findViewById(R.id.fillemail);
        fillpass = (EditText) findViewById(R.id.fillpass);
        buttonlogin = (Button) findViewById(R.id.buttonlogin);
        signup = (TextView) findViewById(R.id.signup);
        data = new Database(this);
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUsername = fillemail.getText().toString();
                String strPassword = fillpass.getText().toString();
                Boolean in = data.checkLogin(strUsername, strPassword);
                if(in == true)
                {
                    Boolean updateSession = data.upgradeSession("ada",1);
                    if(updateSession == true){
                        Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_LONG).show();
                        Intent main = new Intent(LoginActivity.this, MainMenu.class);
                        startActivity(main);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register);
                finish();
            }
        });
    }
}