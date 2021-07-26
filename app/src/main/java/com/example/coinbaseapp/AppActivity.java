package com.example.coinbaseapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AppActivity extends AppCompatActivity {

    String key;
    private SharedViewModel sharedViewModel;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference watchlistRef = database.getReference("/"+user.getUid()+"/watchlist");
    DatabaseReference coinsRef = database.getReference("/"+user.getUid()+"/coins");
    DatabaseReference newChildRef;

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
        loadCoinlist();


        coinsRef.setValue(null);
        //cryptoCoins=new ArrayList<APIQuote>();

        BottomNavigationView bottomNav=findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

        loadLists();    //loads crypto from API and also will load the watchlist using Firebase

        if(user!=null)
        {
            Toast.makeText(this,"User name: "+user.getDisplayName()+"\nUser email: "+user.getEmail().toString() + "\nUser id: " + user.getUid().toString(),Toast.LENGTH_LONG).show();
        }

    }

    private void loadWatchlist() {
        LinkedList<APIQuote> tempList=new LinkedList<>();
        sharedViewModel.setWatchlist(tempList);
    }

    private void loadCoinlist() {
        LinkedList<APIQuote> tempList=new LinkedList<>();
        sharedViewModel.setCoinlist(tempList);
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
                   sharedViewModel.addToCoinlist(profile);
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
/*
        watchlistRef.orderByChild("price").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {

                //cryptoCoins.add(dataSnapshot.getValue(APIQuote.class));

                //List<APIQuote> quotes= (List<APIQuote>) snapshot.getValue();
                APIQuote profile= (snapshot.getValue(APIQuote.class));
                sharedViewModel.addToWatchlist(profile);
                newChildRef = coinsRef.push();
                key = newChildRef.getKey();
                watchlistRef.child(key).setValue(profile);
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

 */



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