package com.example.coinbaseapp;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.LiveData;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class CustomWatchlist extends ArrayAdapter<APIQuote> {
/*
    private Integer imageids[];
    private String companyNames[];
    private String tickerNames[];
    private String changeValues[];
    private String currentValues[];
    private Activity context;

    public CustomWatchlist(Activity context, Integer[] imageids,String companyNames[],
             String tickerNames[],
             String changeValues[],
             String currentValues[])
    {
        super(context,R.layout.list_row, companyNames);
        this.context=context;
        this.imageids=imageids;
        this.companyNames=companyNames;
        this.tickerNames=tickerNames;
        this.changeValues=changeValues;
        this.currentValues=currentValues;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.list_row, null, true);
        TextView textViewCompany = (TextView) row.findViewById(R.id.companyName);
        TextView textViewTicker = (TextView) row.findViewById(R.id.tickerName);
        TextView textViewChangeValue = (TextView) row.findViewById(R.id.changeValue);
        TextView textViewCurrentValue = (TextView) row.findViewById(R.id.currentValue);
        ImageView image = (ImageView) row.findViewById(R.id.tickerImage);

        textViewCompany.setText(companyNames[position]);
        textViewTicker.setText(tickerNames[position]);
        textViewChangeValue.setText(changeValues[position]);
        textViewCurrentValue.setText(currentValues[position]);
        image.setImageResource(imageids[position]);
        return  row;
    }

 */



    //private Integer imageids[];
    private String companyNames[];
    private String tickerNames[];
    private Float changeValues[];
    private Float currentValues[];
    private Activity context;
    private int resourceLayout;

    public CustomWatchlist(Activity context, int resource, LiveData<LinkedList<APIQuote>> coins)
    {
        super(context,resource,coins.getValue());
        //coins=new LinkedList<APIQuote>();
        this.context=context;
        this.resourceLayout=resource;
        //this.coins=coins;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null) {
            row = inflater.inflate(resourceLayout, null, true);
        }

        APIQuote coin=getItem(position);

        if(coin!=null) {
            TextView textViewCompany = (TextView) row.findViewById(R.id.companyName);
            TextView textViewTicker = (TextView) row.findViewById(R.id.tickerName);
            TextView textViewChangeValue = (TextView) row.findViewById(R.id.changeValue);
            TextView textViewCurrentValue = (TextView) row.findViewById(R.id.currentValue);
            ImageView image = (ImageView) row.findViewById(R.id.tickerImage);

            textViewCompany.setText(coin.getName());
            textViewTicker.setText(coin.getSymbol());
            textViewChangeValue.setText(coin.getChange().toString());
            textViewCurrentValue.setText(coin.getChangesPercentage().toString());
            String companyForURL = (coin.getSymbol());
            companyForURL=companyForURL.substring(companyForURL.length()-3);
            companyForURL = companyForURL.toLowerCase();
            URL myUrl = null;
            try {
                myUrl = new URL("https://financialmodelingprep.com/cryptos/bch.svg");
                InputStream inputStream = (InputStream)myUrl.getContent();
                Drawable drawable = Drawable.createFromStream(inputStream, null);
                image.setImageDrawable(drawable);
            } catch (MalformedURLException e) {
                //e.printStackTrace();
                image.setImageResource(        R.drawable.btc
                               );
            } catch (IOException e) {
                //e.printStackTrace();
                image.setImageResource(        R.drawable.btc
                );
            }

            //image.setImageResource(        R.drawable.btc
            //        );
            //String imageURL = "https://financialmodelingprep.com/cryptos/" + companyForURL + ".svg";
            //String imageURL = "https://financialmodelingprep.com/cryptos/bch.svg";
            //Picasso.get().load(imageURL).into(image);
        }
        return  row;
    }


}
