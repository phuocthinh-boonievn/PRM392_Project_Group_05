package com.example.minicapstoneproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
    SharedPreferences sharedPreferences;

    private final static String SHARED_PREF_NAME = "mypref";
    private final static String KEY_EMAIL = "email";
    private final static String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        db = new DBHelper(this);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        btnLogin  = findViewById(R.id.signinBtn);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                if(Email.equals("") || Password.equals("")){
                    Toast.makeText(LoginActivity.this,"Please fill your EMAIL and PASSWORD",Toast.LENGTH_SHORT).show();
                } else if (db.checkEmailPassword(Email,Password)) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_EMAIL,Email);
                    editor.putString(KEY_PASSWORD,Password);
                    editor.apply();
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