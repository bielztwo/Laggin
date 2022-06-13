package com.example.laggin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity{
    EditText fillname, filledemailregister, fillpassword, fillconfirmpassword;
    Button buttonregister;
    TextView log_in;
    Database data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fillname = (EditText) findViewById(R.id.fillname);
        filledemailregister = (EditText) findViewById(R.id.filledemailregister);
        fillpassword = (EditText) findViewById(R.id.fillpassword);
        fillconfirmpassword = (EditText) findViewById(R.id.fillconfirmpassword);
        log_in = (TextView) findViewById(R.id.log_in);
        buttonregister = (Button) findViewById(R.id.buttonregister);
        data = new Database(this);

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(login);
                finish();
            }
        });
        buttonregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUsername = filledemailregister.getText().toString();
                String strPassword = fillpassword.getText().toString();
                String strPassconf = fillconfirmpassword.getText().toString();
                if(strPassword.equals(strPassconf)){
                    Boolean regist = data.insertUser(strUsername, strPassword);
                    if(regist == true){
                        Toast.makeText(getApplicationContext(),"Register Successfull", Toast.LENGTH_LONG). show();
                        Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(login);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"Register Failed", Toast.LENGTH_LONG). show();
                    }

                    }
                else
                {
                    Toast.makeText(getApplicationContext(),"Password not Match", Toast.LENGTH_LONG). show();
                }
            }
        });
    }

}