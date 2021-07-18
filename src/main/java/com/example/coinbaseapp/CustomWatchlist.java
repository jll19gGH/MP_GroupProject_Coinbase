package com.example.coinbaseapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomWatchlist extends ArrayAdapter {
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
}
