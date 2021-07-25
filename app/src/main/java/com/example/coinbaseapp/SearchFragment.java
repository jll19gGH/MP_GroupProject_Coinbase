package com.example.coinbaseapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.LinkedList;

public class SearchFragment extends Fragment {
    private ListView listView;
    private SharedViewModel sharedViewModel;
    CustomWatchlist customWatchlist;
    private EditText searchBox;

    AdapterView.OnItemClickListener searchListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            APIQuote quote = (APIQuote) parent.getItemAtPosition(position);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate=inflater.inflate(R.layout.fragment_search,container,false);

        listView=(ListView) inflate.findViewById(R.id.searchList);
        listView.setOnItemClickListener(searchListener);

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


    }



    }
