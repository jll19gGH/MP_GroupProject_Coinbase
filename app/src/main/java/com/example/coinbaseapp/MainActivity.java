package com.example.coinbaseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    Button registerButton;
    private SharedViewModel sharedViewModel;
    private TickerViewModel viewmodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewmodel = new ViewModelProvider(this).get(TickerViewModel.class);
        loadPortfolio();

        sharedViewModel=new ViewModelProvider(this).get(SharedViewModel.class);
        loadWatchlist();
        loadCoinlist();
        loadGainers();
        loadLosers();
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

    private void loadPortfolio(){
        LinkedList<Portfolio> tempList=new LinkedList<>();
        viewmodel.setTickers(tempList);
    }

    private void loadWatchlist() {
        LinkedList<APIQuote> tempList=new LinkedList<>();
        sharedViewModel.setWatchlist(tempList);
    }

    private void loadCoinlist() {
        LinkedList<APIQuote> tempList=new LinkedList<>();
        sharedViewModel.setCoinlist(tempList);
    }

    private void loadGainers() {
        LinkedList<APIQuote> tempList=new LinkedList<>();
        sharedViewModel.setGainers(tempList);
    }

    private void loadLosers() {
        LinkedList<APIQuote> tempList=new LinkedList<>();
        sharedViewModel.setLosers(tempList);
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