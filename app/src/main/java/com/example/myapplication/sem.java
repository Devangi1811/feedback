package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class sem extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem);
        navigationView=findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void branch(View view) {
        startActivity(new Intent(getApplicationContext(),branch.class));
    }

    public void sem(View view) {
        startActivity(new Intent(getApplicationContext(),sem.class ));
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {



        if (menuItem.getItemId() == R.id.nav_home) {

            startActivity(new Intent(sem.this,branch.class));
        }
        return  true;
    }
}
