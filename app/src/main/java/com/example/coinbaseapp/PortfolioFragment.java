package com.example.coinbaseapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PortfolioFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getChildFragmentManager().beginTransaction().replace(R.id.portfolio_chart_container,new portfolioChart()).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.portfolio_list_container,new porfolioList()).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.portfolio_add_delete_container, new AddDeletePortfolio()).commit();
        return inflater.inflate(R.layout.fragment_portfolio, container, false);
    }
}