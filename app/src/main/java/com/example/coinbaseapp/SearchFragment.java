package com.example.coinbaseapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;

public class SearchFragment extends Fragment {
    private ListView listView;
    private SharedViewModel sharedViewModel;
    CustomWatchlist customWatchlist;
    private EditText searchBox;
    MutableLiveData<Boolean> addtoWatchlist;

    String key;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference watchlistRef = database.getReference("/"+user.getUid()+"/watchlist");
    DatabaseReference newChildRef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate=inflater.inflate(R.layout.fragment_search,container,false);

        listView=(ListView) inflate.findViewById(R.id.searchList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                APIQuote quote = (APIQuote) parent.getItemAtPosition(position);
                sharedViewModel.setCurrentCoin(quote);

                CustomDialogFragmentwAdd d=new CustomDialogFragmentwAdd(quote);
                d.show(getChildFragmentManager(),"dialog");

            }
        });


        searchBox=(EditText) inflate.findViewById(R.id.searchBox);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                customWatchlist.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return inflate;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getCoinlist().observe(getViewLifecycleOwner(), new Observer<LinkedList<APIQuote>>() {
            @Override
            public void onChanged(LinkedList<APIQuote> coins) {
                customWatchlist = new CustomWatchlist(getActivity(), R.layout.list_row, sharedViewModel.getCoinlist());
                listView.setAdapter(customWatchlist);
                listView.setTextFilterEnabled(true);
            }
        });

        addtoWatchlist = new MutableLiveData<>(false);
        addtoWatchlist.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean val) {
                if(val) {
                    sharedViewModel.addToWatchlist(sharedViewModel.getCurrentCoin().getValue());

                    newChildRef = watchlistRef.push();
                    key = newChildRef.getKey();
                    watchlistRef.child(key).setValue(sharedViewModel.getCurrentCoin().getValue());

                    addtoWatchlist.setValue(false);
                }
            }
        });


    }

    }
