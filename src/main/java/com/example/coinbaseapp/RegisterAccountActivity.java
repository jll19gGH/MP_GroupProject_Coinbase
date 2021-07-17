package com.example.coinbaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterAccountActivity extends AppCompatActivity {

    Button registerCancelButton;
    Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        registerCancelButton=findViewById(R.id.registerCancelButton);
        createButton=findViewById(R.id.RegistercreateButton);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchApp();
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

}