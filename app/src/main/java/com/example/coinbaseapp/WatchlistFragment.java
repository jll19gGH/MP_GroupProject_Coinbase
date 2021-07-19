package com.example.coinbaseapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class WatchlistFragment extends Fragment {
private ListView listView;
private Integer imageids[]={
        R.drawable.btc,
        R.drawable.eth
};
private String companyNames[]={
        "Bitcoin",
        "Ethereum"
};
private String tickerNames[]={
        "BTC",
        "ETH"
};
private String changeValues[]={
        "+0.59%",
        "+0.29%"
};
private String currentValues[]={
        "$31,513.76",
        "$1,895.23"
};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View inflate=inflater.inflate(R.layout.fragment_watchlist, container, false);
        ListView listView=(ListView) inflate.findViewById(R.id.watchlistListView);

        CustomWatchlist customWatchlist=new CustomWatchlist(getActivity(), imageids, companyNames,tickerNames,changeValues,currentValues);
       listView.setAdapter(customWatchlist);





        return inflate;
    }
}