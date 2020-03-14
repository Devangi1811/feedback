package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

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
    private SignInButton SignInButton;
    private GoogleSignInClient mGoogleSignInClient;
    private String TAG="SignupActivity";
    private int RC_SIGN_IN=1;
    private CallbackManager mCallbackManager;
    private LoginButton loginButton;
    private static final String Tag="FacebookAuthentication";
    private FirebaseAuth.AuthStateListener authStateListener;
    private AccessTokenTracker accessTokenTracker;
    public static final String USER_ID="userid";

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
        SignInButton=findViewById(R.id.sign_in_button);
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient= GoogleSignIn.getClient(signup.this,gso);
        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        db=FirebaseDatabase.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());
        loginButton=findViewById(R.id.login_button);
        loginButton.setReadPermissions("email","public_profile");
        mCallbackManager=CallbackManager.Factory.create();
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(Tag,"onSuccess"+loginResult);
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(Tag,"onCancel");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d(Tag,"onError");

            }
        });
        btn=findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm(v);
                registeruser();
            }

        });
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser urs=firebaseAuth.getCurrentUser();
                if(urs != null){
                    updateUI(urs);
                }else{
                    updateUI(null);
                }
            }
        };
        accessTokenTracker=new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if(currentAccessToken==null){
                    mAuth.signOut();
                }

            }
        };
    }

    private void handleFacebookToken(AccessToken accessToken) {
        Log.d(Tag,"handleFacebookToken"+accessToken);
        AuthCredential credential= FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(signup.this,"signIn Successfully",Toast.LENGTH_SHORT).show();
                    FirebaseUser urs=mAuth.getCurrentUser();
                    updateUI(urs);
                }else{
                    Toast.makeText(signup.this,"signIn Successfully",Toast.LENGTH_SHORT).show();
                    updateUI(null);
                    Intent intent=new Intent(signup.this,selectfaculty.class);
                    startActivity(intent);
                }

            }
        });
    }

    private void signIn() {
        Toast.makeText(signup.this,"signIn successfully",Toast.LENGTH_SHORT).show();
        Intent signInIntent=mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
        Intent intent=new Intent(signup.this,selectfaculty.class);
        startActivity(intent);
    }
   protected  void onActivityResult(int requestCode ,int resultCode,Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
       super.onActivityResult(requestCode, resultCode, data);
   if(resultCode==RC_SIGN_IN){
       Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
       handleSignInResult(task);
   }
   }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount acc=completedTask.getResult(ApiException.class);
            Toast.makeText(signup.this,"SignIn Successfully",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);
            Intent intent=new Intent(signup.this,selectfaculty.class);
            startActivity(intent);
        }catch (ApiException e){
            Toast.makeText(signup.this,"SignIn failed",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);
        }
    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acct) {
        AuthCredential authCredential= GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(signup.this,"Successful",Toast.LENGTH_SHORT).show();
                    FirebaseUser usr=mAuth.getCurrentUser();
                    updateUI(usr);
                }
                else{
                    Toast.makeText(signup.this,"Failed",Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }

            }
        });
    }

    private void updateUI(FirebaseUser fusr) {
        GoogleSignInAccount accou=GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(accou != null){
            String personName=accou.getDisplayName();
            String personGivenName=accou.getGivenName();
            String perosonFamilyName=accou.getFamilyName();
            String personEmail=accou.getEmail();
            String personId=accou.getId();
            Toast.makeText(signup.this,personName + personEmail,Toast.LENGTH_SHORT).show();
        }
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
                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(signup.this,"Verification email sent to"+Email,Toast.LENGTH_SHORT).show();
                        }
                    });
                    Toast.makeText(signup.this,"User login successfully",Toast.LENGTH_SHORT).show();
                    Map<String,String> user=new HashMap<>();
                    user.put("email",email);
                    user.put("password",password);
                    db.getReference().child("users").child(mAuth.getCurrentUser().getUid()).setValue(user);

                    Intent intent = new Intent(signup.this, selectfaculty.class);
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
        Intent intent=new Intent(signup.this,selectfaculty.class);
        startActivity(intent);
    }
    protected void onStart() {

        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }
    protected void onStop() {

        super.onStop();
        if(authStateListener != null){
            mAuth.removeAuthStateListener(authStateListener);
        }
    }





    public void selectfaculty(View view) {
        startActivity(new Intent(getApplicationContext(),selectfaculty.class));
    }

    public void subject(View view) {
        startActivity(new Intent(getApplicationContext(),subject.class));
    }
}
