package com.example.coinbaseapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Random;

public class portfolioChart extends Fragment {

    PieChart pieChart;
    TickerViewModel mViewModel;
    TextView porthomebal;
    Random rnd = new Random();
    float total = 0;
    double portfolioTotal = 0;
    float crypto = 0;
    TextView portValue;

    public portfolioChart() {
        // Required empty public constructor
    }

    public static portfolioChart newInstance() {
        return new portfolioChart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_portfolio_chart, container, false);
        mViewModel =  new ViewModelProvider(requireActivity()).get(TickerViewModel.class);
        pieChart = inflate.findViewById(R.id.piechart);
        portValue = inflate.findViewById(R.id.amount);
        porthomebal = getActivity().findViewById(R.id.portHomeBalance);
        updateList();
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel =  new ViewModelProvider(requireActivity()).get(TickerViewModel.class);
        mViewModel.getTickers().observe(getViewLifecycleOwner(), new Observer<LinkedList<Portfolio>>() {
            @Override
            public void onChanged(LinkedList<Portfolio> tickers) {
                updateList();
            }
        });
    }

    void updateList(){
        pieChart.clearChart();
        total = 0;
        portfolioTotal = 0;

        for (int i = 0; i < mViewModel.size(); i++) {
            total += mViewModel.getCryptoAmount(i);
            portfolioTotal += mViewModel.getCryptoAmount(i);
        }
        portValue.setText("$ "+ String.format(Locale.US,"%.2f",portfolioTotal));
        mViewModel.ChangePort("$ "+ String.format(Locale.US,"%.2f",portfolioTotal));

        for (int i = 0; i < mViewModel.size(); i++) {
            pieChart.addPieSlice(new PieModel(mViewModel.getName(i), (mViewModel.getCryptoAmount(i)/total) * 100, Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))));
        }
        pieChart.update();
        pieChart.startAnimation();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateList();
    }

}