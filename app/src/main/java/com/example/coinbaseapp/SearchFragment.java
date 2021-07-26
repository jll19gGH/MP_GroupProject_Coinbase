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
    //private boolean addtoWatchlist=false;
    MutableLiveData<Boolean> addtoWatchlist;

    String key;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference watchlistRef = database.getReference("/"+user.getUid()+"/watchlist");
    DatabaseReference newChildRef;


    /*
    AdapterView.OnItemClickListener searchListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            APIQuote quote = (APIQuote) parent.getItemAtPosition(position);



            CustomDialogFragmentwAdd d=new CustomDialogFragmentwAdd();
            d.show(getChildFragmentManager(),"dialog");



        }
    };

 */

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

/*
                TextView textViewCompany = (TextView) inflate.findViewById(R.id.dialogCompanyName);
                TextView textViewTicker = (TextView) inflate.findViewById(R.id.dialogTickerName);
                TextView textViewChangeValue = (TextView) inflate.findViewById(R.id.dialogChange);
                TextView textViewCurrentValue = (TextView) inflate.findViewById(R.id.dialogPrice);
                ImageView image = (ImageView) inflate.findViewById(R.id.dialogTickerImage);

                textViewCompany.setText("APPLE");


                String name= quote.getName();
                name=name.substring(0,name.length()-3);

                textViewCompany.setText(name);
                textViewTicker.setText(quote.getSymbol());
                Float price= quote.getPrice();
                NumberFormat priceFormat=new DecimalFormat("0.00");
                String priceFormatted=priceFormat.format(price);
                textViewChangeValue.setText("$ "+priceFormatted);
                Float changePercent= quote.getChangesPercentage();
                NumberFormat changeFormat=new DecimalFormat("0.0000");
                String changeFormatted=changeFormat.format(changePercent);
                textViewCurrentValue.setText(changeFormatted+" %");
                String imageURL = "https://cryptologos.cc/logos/usd-coin-usdc-logo.png?v=013";
                Picasso.get().load(imageURL).fit().into(image);
*/
                //sharedViewModel.setCurrentCoin(quote);


                CustomDialogFragmentwAdd d=new CustomDialogFragmentwAdd(quote);
                d.show(getChildFragmentManager(),"dialog");


               //if(addtoWatchlist) {
                //  Toast.makeText(getActivity(),"addtowatch",Toast.LENGTH_LONG).show();
                 // sharedViewModel.addToWatchlist(sharedViewModel.getCurrentCoin().getValue());
             // }
            }
        });

        //addtoWatchlist=new MutableLiveData<>();
        //addtoWatchlist.postValue(false);
        /*
        addtoWatchlist.setValue(Boolean.valueOf(false));
        addtoWatchlist.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(addtoWatchlist.getValue()) {
                    sharedViewModel.addToWatchlist(sharedViewModel.getCurrentCoin().getValue());
                }
                addtoWatchlist.setValue(false);
            }
        });

         */

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
                //CustomWatchlist customWatchlist = new CustomWatchlist(getActivity(), imageids, companyNames, tickerNames, changeValues, currentValues);

                customWatchlist = new CustomWatchlist(getActivity(), R.layout.list_row, sharedViewModel.getCoinlist());
                //ArrayAdapter<String> adapter =  new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, sharedViewModel.getWatchList().getValue());
                //listView.setAdapter(adapter);
                listView.setAdapter(customWatchlist);
                listView.setTextFilterEnabled(true);
            }
        });

        addtoWatchlist = new MutableLiveData<>(false);
        addtoWatchlist.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean val) {
                if(val) {
                    Toast.makeText(getActivity(), "change received", Toast.LENGTH_LONG).show();
                    sharedViewModel.addToWatchlist(sharedViewModel.getCurrentCoin().getValue());

                    newChildRef = watchlistRef.push();
                    key = newChildRef.getKey();
                    watchlistRef.child(key).setValue(sharedViewModel.getCurrentCoin().getValue());

                    addtoWatchlist.setValue(false);
                }
                //CustomWatchlist customWatchlist = new CustomWatchlist(getActivity(), imageids, companyNames, tickerNames, changeValues, currentValues);

               // customWatchlist = new CustomWatchlist(getActivity(), R.layout.list_row, sharedViewModel.getCoinlist());
                //ArrayAdapter<String> adapter =  new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, sharedViewModel.getWatchList().getValue());
                //listView.setAdapter(adapter);
               // listView.setAdapter(customWatchlist);
                //listView.setTextFilterEnabled(true);
            }
        });


    }

    }
