package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class selectfaculty extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    CardView cv1,cv2,cv3,cv4,cv5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectfaculty);
        Spinner spinner=findViewById(R.id.spinner1);
        Spinner spinner1=findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(selectfaculty.this,R.array.numbers,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(selectfaculty.this,R.array.numbers1,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(selectfaculty.this);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(selectfaculty.this);
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        cv1=findViewById(R.id.cd1);
        cv2=findViewById(R.id.cd2);
        cv3=findViewById(R.id.cd3);
        cv4=findViewById(R.id.cd4);
        cv5=findViewById(R.id.cd5);
        cv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(selectfaculty.this,question.class);
                i.putExtra("subjectname","ai");
               startActivity(i);

            }
        });
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(selectfaculty.this,question.class);
                i.putExtra("subjectname","pp");
                startActivity(i);

            }
        });
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(selectfaculty.this,question.class);
                i.putExtra("subjectname","cns");
                startActivity(i);

            }
        });
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(selectfaculty.this,question.class);
                i.putExtra("subjectname","android");
                startActivity(i);

            }
        });
        cv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(selectfaculty.this,question.class);
                i.putExtra("subjectname","iot");
                startActivity(i);

            }
        });
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void subject(View view) {
        startActivity(new Intent(getApplicationContext(),subject.class));
    }

    public void question(View view) {
        startActivity(new Intent(getApplicationContext(),question.class));
    }
}
