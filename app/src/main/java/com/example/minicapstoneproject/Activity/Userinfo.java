package com.example.minicapstoneproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minicapstoneproject.Database.DBHelper;
import com.example.minicapstoneproject.R;

public class Userinfo extends AppCompatActivity {

    DBHelper db;
    SharedPreferences sharedPreferences;
    private final static String SHARED_PREF_NAME = "mypref";
    private final static String KEY_EMAIL = "email";
    TextView name, email;
    ImageView backBtn;
    Button logoutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        logoutbtn = findViewById(R.id.logoutbtn);
        backBtn = findViewById(R.id.backBtn);

        db = new DBHelper(this);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String txtEmail = sharedPreferences.getString(KEY_EMAIL,null);
        String txtName = db.getNameByEmail(txtEmail);
        email.setText(txtEmail);
        name.setText(txtEmail);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                finish();
                Toast.makeText(getApplicationContext(),"Logout successfully",Toast.LENGTH_SHORT).show();
            }
        });
    }
}