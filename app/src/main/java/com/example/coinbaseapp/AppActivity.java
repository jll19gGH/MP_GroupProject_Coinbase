package com.example.coinbaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        BottomNavigationView bottomNav=findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment=null;

            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment=new HomeFragment();
                    break;
                case R.id.nav_portfolio:
                    selectedFragment=new PortfolioFragment();
                    break;
                case R.id.nav_top_movers:
                    selectedFragment=new Top10Fragment();
                    break;
                case R.id.nav_search:
                    selectedFragment=new SearchFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
            };
}