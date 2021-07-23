package com.example.coinbaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AppActivity extends AppCompatActivity {

    String key;
    private SharedViewModel sharedViewModel;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference coinsRef = database.getReference("/coins");
    DatabaseReference newChildRef;
    //ArrayList<APIQuote> cryptoCoins;

    private void getCryptoList()
    {
        //return cryptoCoins;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        sharedViewModel=new ViewModelProvider(this).get(SharedViewModel.class);
        loadWatchlist();


        coinsRef.setValue(null);
        //cryptoCoins=new ArrayList<APIQuote>();

        BottomNavigationView bottomNav=findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

        coinsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                //cryptoCoins.add(dataSnapshot.getValue(APIQuote.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot,String prevChildKey) {

            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        loadLists();    //loads crypto from API and also will load the watchlist using Firebase
    }

    private void loadWatchlist() {
        LinkedList<APIQuote> tempList=new LinkedList<>();
        sharedViewModel.setWatchlist(tempList);
    }

    private void loadLists() {
        // https://financialmodelingprep.com/api/v3/quote/AAPL?apikey=demo
        ANRequest req = AndroidNetworking.get("https://financialmodelingprep.com/api/v3/quotes/crypto")
                .addQueryParameter("apikey", "4c83ea6a2f1e03eb2fdb0ba3768d68fb")
                .setPriority(Priority.LOW)
                .build();
        req.getAsObjectList(APIQuote.class, new ParsedRequestListener<List<APIQuote>>() {
            @Override
            public void onResponse(List<APIQuote> quotes) {
                for (APIQuote profile : quotes)
               {
                   sharedViewModel.addToWatchlist(profile);
                  newChildRef = coinsRef.push();
                  key = newChildRef.getKey();
                  coinsRef.child(key).setValue(profile);
               }

              //Toast.makeText(AppActivity.this, cryptoCoins.get(cryptoCoins.size()-1).getName().toString(), Toast.LENGTH_LONG).show();


            }
            //@Override
            public void onError(ANError anError) {
                Toast.makeText(AppActivity.this,"ERROR: Ticker does not exist", Toast.LENGTH_LONG).show();
            }

        });


        //Toast.makeText(AppActivity.this, quotes.get(quotes.size()-1).getName().toString(), Toast.LENGTH_LONG).show();

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