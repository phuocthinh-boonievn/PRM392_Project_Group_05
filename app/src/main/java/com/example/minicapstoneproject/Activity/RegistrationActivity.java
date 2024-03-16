package com.example.minicapstoneproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.minicapstoneproject.Database.DBHelper;
import com.example.minicapstoneproject.Model.User;
import com.example.minicapstoneproject.R;

public class RegistrationActivity extends AppCompatActivity {

    EditText name,password,repassword;
    Button signup;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        signup = findViewById(R.id.signupBtn);
        db = new DBHelper(this);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtname = name.getText().toString();
                String txtpassword = password.getText().toString();
                String txtrepassword = repassword.getText().toString();

                if(txtname.equals("") || txtpassword.equals("") || txtrepassword.equals("")){
                    Toast.makeText(getApplicationContext(),"Please Fill All Field",Toast.LENGTH_SHORT).show();
                }else if (db.checkName(txtname)){
                    Toast.makeText(getApplicationContext(),"Existed Email",Toast.LENGTH_SHORT).show();
                }else if(txtpassword.equals(txtrepassword)){
                    User user = new User(txtname,txtpassword);
                    boolean insert = db.insertUser(user);
                    if(insert){
                        Toast.makeText(getApplicationContext(),"Registed Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    }else {
                        Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Confirm Password Does Not Match Your Password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void signin(View view) {
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
    }
}