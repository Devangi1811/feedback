package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;



public class login extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN=
            Pattern.compile("^"+
                    "(?=.*[0-9])"+
                    "(?=.*[a-z])"+
                    "(?=.*[A-Z])"+
                    "(?=.*[@#$%^&+=])"+
                    // "(?=\\s+$)"+
                    ".{4,}");
    // "$");

    private EditText Email;
    private EditText Password;
    private Button btn;
    private FirebaseAuth mAuth;
    String email;
    String password;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        btn = findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm(v);
                loginuser();


            }
        });


    }
    private boolean validateEmail(){
        String email=Email.getText().toString().trim();

        if(email.isEmpty()){
            Email.setError("Field can't empty");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("Email too weak");
            return false;
        }
        else{
            Email.setError(null);
            return true;
        }
    }
    private boolean validatePassword(){
        String password=Password.getText().toString().trim();

        if(password.isEmpty()){
            Password.setError("Field can't empty");
            return false;
        }else if(!PASSWORD_PATTERN.matcher(password).matches()){
            Password.setError("Password too weak");
            return false;
        }
        else{
            Password.setError(null);
            return true;
        }
    }

    void loginuser() {
      email=Email.getText().toString().trim();
      password=Password.getText().toString().trim();
      validateEmail();
      validatePassword();

      mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
              if (task.isSuccessful()) {
                  Toast.makeText(login.this,"User login successfully",Toast.LENGTH_SHORT).show();
                  Intent intent = new Intent(login.this, subject.class);
                  startActivity(intent);


              } else {
                  Toast.makeText(login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
              }

          }
      });

    }

    public void confirm(View v){
        if(!validateEmail() | !validatePassword()){
            return;
        }

        String string ="Email"+Email.getText().toString();
        string +="\n";
        string +="Password"+Password.getText().toString();
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(login.this,subject.class);
        startActivity(intent);
    }




    public void btn_button1(View view) {
        startActivity(new Intent(getApplicationContext(), signup.class));
    }

    public void edit_text(View view) {
        startActivity(new Intent(getApplicationContext(), sendotp.class));
    }

    public void sem(View view) {
        startActivity(new Intent(getApplicationContext(), sem.class));
    }

    public void signup(View view) {
        startActivity(new Intent(getApplicationContext(),signup.class));
    }
}


