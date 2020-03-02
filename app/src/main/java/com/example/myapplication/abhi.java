package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class abhi extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abhi);
    }



   // @Override
    //public boolean onNavigationItemSelected(MenuItem menuItem) {
       // return  true;
   // }



    public void login(View view) {startActivity(new Intent(getApplicationContext(),login.class));
    }
    public void account(View view) {startActivity(new Intent(getApplicationContext(),account.class));
    }

}
