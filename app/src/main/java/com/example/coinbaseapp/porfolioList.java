package com.example.coinbaseapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.LinkedList;


public class porfolioList extends Fragment {

    ListView listView;
    TickerViewModel mViewModel;

    public porfolioList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_porfolio_list, container, false);
        listView = inflate.findViewById(R.id.cyptoList);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel =  new ViewModelProvider(requireActivity()).get(TickerViewModel.class);
        mViewModel.getTickers().observe(getViewLifecycleOwner(), new Observer<LinkedList<Portfolio>>() {
            @Override
            public void onChanged(LinkedList<Portfolio> tickers) {
                PortfolioCustom customWatchlist = new PortfolioCustom(getActivity(), R.layout.potfoliorow, mViewModel.getTickers());
                listView.setAdapter(customWatchlist);
            }
        });
    }


}