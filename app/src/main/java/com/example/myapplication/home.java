package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void btn_button1(View view) {
        startActivity(new Intent(getApplicationContext(), login.class));
    }

    public void btn_button2(View view) {
        startActivity(new Intent(getApplicationContext(), home.class));


    }

    public void btn_button3(View view) {
        startActivity(new Intent(getApplicationContext(), signup.class));
    }
}

