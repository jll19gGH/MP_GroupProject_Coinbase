package com.example.coinbaseapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.lifecycle.LiveData;

import java.util.LinkedList;
import java.util.List;

public class PortfolioCustom extends ArrayAdapter<Portfolio> {
    private Activity context;
    private int resourceLayout;
    private List<Portfolio> coins;

    public PortfolioCustom(Activity context, int resource, LiveData<LinkedList<Portfolio>> coins)
    {
        super(context,resource,coins.getValue());
        this.coins=new LinkedList<Portfolio>();
        this.coins.addAll(coins.getValue());
        this.context=context;
        this.resourceLayout=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null) {
            row = inflater.inflate(resourceLayout, null, true);
        }

        Portfolio coin=getItem(position);

        if(coin!=null) {
            TextView textViewCompany = (TextView) row.findViewById(R.id.cryptoName);
            TextView textViewTicker = (TextView) row.findViewById(R.id.cryptoTicker);
            TextView textViewValue = (TextView) row.findViewById(R.id.CrytpoValue);
            TextView textViewAmount = (TextView) row.findViewById(R.id.CryptoAmount);

            String ticker = coin.getCrytpoTicker();
            ticker = ticker.substring(0, ticker.length() - 3);
            textViewTicker.setText(ticker);

            textViewCompany.setText(coin.getCryptoName());
            textViewValue.setText(coin.getPrice());
            textViewAmount.setText(coin.getAmount());
            /*Float price = coin.getPrice();
            NumberFormat priceFormat = new DecimalFormat("0.00");
            String priceFormatted = priceFormat.format(price);
            textViewValue.setText("$ " + priceFormatted);
            Float changePercent = coin.getChangesPercentage();
            NumberFormat changeFormat = new DecimalFormat("0.0000");
            String changeFormatted = changeFormat.format(changePercent);
            textViewAmount.setText(changeFormatted + " %");*/

        }
        return  row;
    }

}
