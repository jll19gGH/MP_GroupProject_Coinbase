package com.example.coinbaseapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class WatchlistFragment extends Fragment {
private ListView listView;
private SharedViewModel sharedViewModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View inflate=inflater.inflate(R.layout.fragment_watchlist, container, false);
        listView=(ListView) inflate.findViewById(R.id.watchlistListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                APIQuote quote = (APIQuote) parent.getItemAtPosition(position);
                //sharedViewModel.setCurrentCoin(quote);

                CustomDialogFragmentNoAdd d=new CustomDialogFragmentNoAdd(quote);
                d.show(getChildFragmentManager(),"dialog2");
            }
        });

        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getWatchList().observe(getViewLifecycleOwner(), new Observer<LinkedList<APIQuote>>() {
            @Override
            public void onChanged(LinkedList<APIQuote> coins) {
                CustomWatchlist customWatchlist = new CustomWatchlist(getActivity(), R.layout.list_row, sharedViewModel.getWatchList());
                listView.setAdapter(customWatchlist);
            }
        });


    }
}