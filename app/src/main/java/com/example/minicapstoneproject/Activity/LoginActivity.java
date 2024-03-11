package com.example.minicapstoneproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.minicapstoneproject.Database.DBHelper;
import com.example.minicapstoneproject.R;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button btnLogin;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        db = new DBHelper(this);

        btnLogin  = findViewById(R.id.signinBtn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                if(Email.equals("") || Password.equals("")){
                    Toast.makeText(LoginActivity.this,"Please fill your EMAIL and PASSWORD",Toast.LENGTH_SHORT).show();
                } else if (db.checkEmailPassword(Email,Password)) {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    Toast.makeText(getApplicationContext(),"Login successfully",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this,"Wrong Email or Password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void signup(View view) {
        startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
    }
}