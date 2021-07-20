package com.example.coinbaseapp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

public class LoginActivity extends AppCompatActivity {

    EditText emailTV;
    TextInputEditText passwordTV;
    TextInputLayout password_error;//=(TextInputLayout) findViewById(R.id.password_input_layout);
    Button cancelButton;
    Button signinButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        emailTV=(EditText) findViewById(R.id.editTextEmailAddress);
       passwordTV=(TextInputEditText) findViewById(R.id.editTextPassword);
       password_error=(TextInputLayout) findViewById(R.id.password_input_layout);
        cancelButton=findViewById(R.id.loginCancelButton);
        signinButton=findViewById(R.id.signinButton);

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=emailTV.getText().toString();
               String password=passwordTV.getText().toString();

               if((email.matches(""))||(password.matches("")))
               {
                   password_error.setError("Email and/or password are invalid");
               }
               else {
                   attemptSignin(email, password);
               }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void launchApp()
    {
        Intent intent=new Intent(this,AppActivity.class);
        startActivity(intent);
    }

    public void attemptSignin(@NonNull String email, @NonNull String password)
    {
        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user=mAuth.getCurrentUser();
                            launchApp();
                        } else {
                            password_error.setError("Email and/or password are invalid");
                        }
                    }
                });


    }

}