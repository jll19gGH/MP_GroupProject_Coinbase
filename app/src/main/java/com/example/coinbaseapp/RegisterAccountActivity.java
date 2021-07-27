package com.example.coinbaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import static android.content.ContentValues.TAG;

public class RegisterAccountActivity extends AppCompatActivity {

    Button registerCancelButton;
    Button createButton;
    EditText firstTV;
    EditText lastTV;
    EditText emailTV;
    TextInputEditText passwordTV;
    TextInputLayout error_layouts;
    CheckBox ageVerifierBox;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        registerCancelButton=findViewById(R.id.registerCancelButton);
        createButton=findViewById(R.id.RegistercreateButton);

        firstTV=findViewById(R.id.register_first_name);
        lastTV=findViewById(R.id.register_lastName);
        emailTV=findViewById(R.id.register_email_address);
        passwordTV=findViewById(R.id.register_password);
        error_layouts=findViewById(R.id.registration_layout);
        ageVerifierBox=findViewById(R.id.register_checkBox);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //launchApp();

                String first=firstTV.getText().toString();
                String last=lastTV.getText().toString();
                String email=emailTV.getText().toString();
                String password=passwordTV.getText().toString();

                if((first.matches(""))||(first.matches(""))||(first.matches(""))||(first.matches(""))||!(ageVerifierBox.isChecked()))
                {
                    error_layouts.setError("Make sure all fields are complete");
                }
                else if(!(email.contains("@")))
                {
                    error_layouts.setError("Make sure your email is valid");

                }
                else if(password.length()<6)
                {
                    error_layouts.setError("Password must be at least 6 characters");
                }
                else
                {
                    attemptRegister(email,password);
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName((firstTV.getText().toString())+" "+(lastTV.getText().toString()))
                            .build();

                    user.updateProfile(profileUpdates);

                    launchApp();
                }
            }
        });

        registerCancelButton.setOnClickListener(new View.OnClickListener() {
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

    public void attemptRegister(@NonNull String email, @NonNull String password)
    {
        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                        } else {
                            error_layouts.setError("Something went wrong. Please try again");
                        }
                    }
                });


    }

}