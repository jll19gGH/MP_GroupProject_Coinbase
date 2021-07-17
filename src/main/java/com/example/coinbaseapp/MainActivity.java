package com.example.coinbaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton=findViewById(R.id.loginButton);
        registerButton=findViewById(R.id.registerButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchLogin();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchRegistration();
            }
        });
    }

    public void launchLogin()
    {
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void launchRegistration()
    {
        Intent intent=new Intent(this,RegisterAccountActivity.class);
        startActivity(intent);
    }
}