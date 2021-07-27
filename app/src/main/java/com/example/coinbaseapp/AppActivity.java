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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AppActivity extends AppCompatActivity {

    String key;
    private SharedViewModel sharedViewModel;
    private TickerViewModel viewmodel;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference portfolioList = database.getReference("/"+user.getUid()+"/portfolio");
    DatabaseReference watchlistRef = database.getReference("/"+user.getUid()+"/watchlist");
    DatabaseReference coinsRef = database.getReference("/"+user.getUid()+"/coins");
    DatabaseReference newChildRef;
    boolean flag=true;

    private void getCryptoList()
    {
        //return cryptoCoins;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        viewmodel = new ViewModelProvider(this).get(TickerViewModel.class);
        sharedViewModel=new ViewModelProvider(this).get(SharedViewModel.class);
        loadWatchlist();
        loadCoinlist();
        loadGainers();
        loadLosers();
        loadList();

        coinsRef.setValue(null);

        BottomNavigationView bottomNav=findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

        loadLists();    //loads crypto from API and also will load the watchlist using Firebase

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
            }
            //@Override
            public void onError(ANError anError) {
                Toast.makeText(AppActivity.this,"ERROR: Ticker does not exist", Toast.LENGTH_LONG).show();
            }

        });

        watchlistRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    if(flag)
                    sharedViewModel.addToWatchlist(postSnapshot.getValue(APIQuote.class));
                }
                flag=false;
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

        /*portfolioList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren())
                        viewmodel.setList(postSnapshot.getValue(Portfolio.class));
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });*/
    }

    private void loadList(){
        LinkedList<Portfolio> tempList=new LinkedList<>();

        /*portfolioList.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren())
                    tempList.add(postSnapshot.getValue(Portfolio.class));
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });*/

        viewmodel.setTickers(tempList);
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