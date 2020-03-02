package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class signup extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN=
            Pattern.compile("^"+
                    "(?=.*[0-9])"+
                    "(?=.*[a-z])"+
                    "(?=.*[A-Z])"+
                    "(?=.*[@#$%^&+=])"+
                    // "(?=\\s+$)"+
                    ".{4,}");
    // "$");
    private EditText Username;
    private EditText Password;
    private EditText Cpassword;
    private EditText Email;
    private EditText Phoneno;
    private Button btn;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Username=findViewById(R.id.username);
        Password=findViewById(R.id.password);
        Cpassword=findViewById(R.id.cpassword);
        Email=findViewById(R.id.email);
        mAuth = FirebaseAuth.getInstance();
        Phoneno=findViewById(R.id.phoneno);
        db=FirebaseDatabase.getInstance();
        btn=findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm(v);
                registeruser();
            }

        });

    }
    private boolean validateUsername(){
        String username=Username.getText().toString().trim();

        if(username.isEmpty()){
            Username.setError("Field can't be empty");
            return false;
        }else if(username.length()>15){
            Username.setError("Username too long");
            return false;
        }else{
            Username.setError(null);
            return true;
        }
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
    private boolean validatePhoneno(){
        String phone=Phoneno.getText().toString().trim();

        if(phone.isEmpty()) {
            Phoneno.setError("Field can't be empty");
            return false;
        }
        else if(phone.length()>10){
            Phoneno.setError("Phone number too long");
            return false;
        }else{
            Phoneno.setError(null);
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
    private boolean validateCpassword(){
        String cpassword=Cpassword.getText().toString().trim();
        String password=Password.getText().toString().trim();

        if(cpassword.isEmpty()){
            Cpassword.setError("Field can't empty");
            return false;
        }else if(!password.equals(cpassword)){
            Cpassword.setError("Password does not match");
            return false;
        }
        else{
            Cpassword.setError(null);
            return true;
        }
    }
    void registeruser(){
        email=Email.getText().toString().trim();
        password=Password.getText().toString().trim();
        validateEmail();
        validatePassword();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(signup.this,"User login successfully",Toast.LENGTH_SHORT).show();
                    Map<String,String> user=new HashMap<>();
                    user.put("email",email);
                    user.put("password",password);
                    db.getReference().child("users").child(mAuth.getCurrentUser().getUid()).setValue(user);

                    Intent intent = new Intent(signup.this, subject.class);
                    startActivity(intent);


                } else {
                    Toast.makeText(signup.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void confirm(View v){
        if(!validateUsername() | !validateEmail() | !validatePassword() | !validatePhoneno() | !validateCpassword()){
            return;
        }
        String string="Username"+Username.getText().toString();
        string +="\n";
        string +="Email"+Email.getText().toString();
        string +="\n";
        string +="Password"+Phoneno.getText().toString();
        string +="\n";
        string +="Phoneno"+Password.getText().toString();
        string +="\n";
        string +="confirmPassword"+Cpassword.getText().toString();
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(signup.this,subject.class);
        startActivity(intent);
    }


    public void btn_button1(View view) {
        startActivity(new Intent(getApplicationContext(), login.class));
    }

    public void edit_text(View view) {
        startActivity(new Intent(getApplicationContext(), sendotp.class));
    }
    public void subject(View view) {startActivity(new Intent(getApplicationContext(),subject.class));
    }
}
