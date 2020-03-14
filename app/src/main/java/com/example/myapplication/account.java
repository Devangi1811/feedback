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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class account extends AppCompatActivity{
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
    TextView tv1;
    String email;
    String password;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN=1;
    private com.google.android.gms.common.SignInButton SignInButton;
    private CallbackManager mCallbackManager;
    private LoginButton loginButton;
    private static final String Tag="FacebookAuthentication";
    private FirebaseAuth.AuthStateListener authStateListener;
    private AccessTokenTracker accessTokenTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        btn = findViewById(R.id.login);
        tv1 = findViewById(R.id.acco);
        mAuth = FirebaseAuth.getInstance();
        SignInButton=findViewById(R.id.sign_in_button);
        loginButton=findViewById(R.id.login_button);
        loginButton.setReadPermissions("email","public_profile");
        mCallbackManager=CallbackManager.Factory.create();
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient= GoogleSignIn.getClient(account.this,gso);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm(v);
                loginuser();
            }
        });
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
        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
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
        mAuth.signInWithCredential(credential).addOnCompleteListener(account.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(account.this,"signIn Successfully",Toast.LENGTH_SHORT).show();
                    FirebaseUser urs=mAuth.getCurrentUser();
                    updateUI(urs);
                }else{
                    Toast.makeText(account.this,"signIn Successfully",Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }

            }
        });
    }

    private void signIn() {
        Toast.makeText(account.this,"signIn successfully",Toast.LENGTH_SHORT).show();
        Intent signInIntent=mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
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
            Toast.makeText(account.this,"SignIn Successfully",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);
        }catch (ApiException e){
            Toast.makeText(account.this,"SignIn failed",Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(null);
        }
    }

    private void FirebaseGoogleAuth(GoogleSignInAccount acct) {
        AuthCredential authCredential= GoogleAuthProvider.getCredential(acct.getIdToken(),null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(account.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(account.this,"Successful",Toast.LENGTH_SHORT).show();
                    FirebaseUser usr=mAuth.getCurrentUser();
                    updateUI(usr);
                }
                else{
                    Toast.makeText(account.this,"Failed",Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }

            }
        });
    }

    private void updateUI(FirebaseUser usr) {
        GoogleSignInAccount accou=GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(accou != null){
            String personName=accou.getDisplayName();
            String personGivenName=accou.getGivenName();
            String perosonFamilyName=accou.getFamilyName();
            String personEmail=accou.getEmail();
            String personId=accou.getId();
            Toast.makeText(account.this,personName + personEmail,Toast.LENGTH_SHORT).show();
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
                if(task.isSuccessful()) {
                    mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(account.this,"Verification email sent to"+Email,Toast.LENGTH_SHORT).show();
                        }
                    });
                    Toast.makeText(account.this,"User login successfully",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(account.this, selectfaculty.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(account.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
        Intent intent=new Intent(account.this,selectfaculty.class);
        startActivity(intent);
    }
    protected void onStart() {

        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }
    protected void onStop() {

        super.onStop();
        if (authStateListener != null) {
            mAuth.removeAuthStateListener(authStateListener);
        }
    }

    public void signup(View view) {startActivity(new Intent(getApplicationContext(),signup.class));
    }

    public void selectfaculty(View view) {
        startActivity(new Intent(getApplicationContext(),selectfaculty.class));
    }
}
