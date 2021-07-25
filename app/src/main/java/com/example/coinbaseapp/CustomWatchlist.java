package com.example.coinbaseapp;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CustomWatchlist extends ArrayAdapter<APIQuote> implements Filterable {
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
    private List<APIQuote> coins;
    private List<APIQuote> filteredCoins;
    private CoinsFilter filter;
    //private ItemFilter itemFilter=new ItemFilter();

    public CustomWatchlist(Activity context, int resource, LiveData<LinkedList<APIQuote>> coins)
    {
        super(context,resource,coins.getValue());
        this.coins=new LinkedList<APIQuote>();
        this.coins.addAll(coins.getValue());
        //this.coins=coins.getValue();
        this.filteredCoins=new LinkedList<>();
        this.filteredCoins.addAll(coins.getValue());
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

        APIQuote coin=getItem(position);

        if(coin!=null) {
            TextView textViewCompany = (TextView) row.findViewById(R.id.companyName);
            TextView textViewTicker = (TextView) row.findViewById(R.id.tickerName);
            TextView textViewChangeValue = (TextView) row.findViewById(R.id.changeValue);
            TextView textViewCurrentValue = (TextView) row.findViewById(R.id.currentValue);
            ImageView image = (ImageView) row.findViewById(R.id.tickerImage);

            String name= coin.getName();
            name=name.substring(0,name.length()-3);

            textViewCompany.setText(name);
            textViewTicker.setText(coin.getSymbol());
            Float price= coin.getPrice();
            NumberFormat priceFormat=new DecimalFormat("0.00");
            String priceFormatted=priceFormat.format(price);
            textViewChangeValue.setText("$ "+priceFormatted);
            Float changePercent= coin.getChangesPercentage();
            NumberFormat changeFormat=new DecimalFormat("0.0000");
            String changeFormatted=changeFormat.format(changePercent);
            textViewCurrentValue.setText(changeFormatted+" %");
            String imageURL = "https://cryptologos.cc/logos/usd-coin-usdc-logo.png?v=013";
            Picasso.get().load(imageURL).fit().into(image);


            //image.setImageResource(        R.drawable.btc
                 //   );
            /*
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

             */

            //image.setImageResource(        R.drawable.btc
            //        );
            //String imageURL = "https://financialmodelingprep.com/cryptos/" + companyForURL + ".svg";
            //String imageURL = "https://financialmodelingprep.com/cryptos/bch.svg";
            //Picasso.get().load(imageURL).into(image);
        }
        return  row;
    }


    @Override
    public Filter getFilter() {
        if(filter==null) {
            filter=new CoinsFilter();
        }
        return filter;
    }

    private class CoinsFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String match;
            match = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if(match != null && match.length() > 0)
            {
                LinkedList<APIQuote> filteredItems = new LinkedList<APIQuote>();

                for(int i = 0; i < coins.size(); i++)
                {
                    APIQuote profile = coins.get(i);
                    if(profile.toString().toLowerCase().contains(match))
                    {
                        filteredItems.add(profile);
                    }
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            }
            else
            {
                synchronized(this)
                {
                    result.values = coins;
                    result.count = coins.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            filteredCoins = (LinkedList<APIQuote>)results.values;
            notifyDataSetChanged();
            clear();
            for(int i = 0, l = results.count; i < l; i++)
                add(filteredCoins.get(i));
            notifyDataSetInvalidated();
        }
    }

    }

  //  @Override
  //  public Filter getFilter() {
    //    return filter;
   // }
/*
    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            LinkedList<APIQuote> filteredList=new LinkedList<>();

            if(charSequence.toString().isEmpty()) {
                //filteredList.addAll(coins.val);
            }
            else
            {
                for(APIQuote profile:coins) {
                    if(profile.getName().toLowerCase().contains(charSequence.toString().toLowerCase()))
                    {
                        filteredList.add(profile);
                    }
                }
            }

            FilterResults filterResults=new FilterResults();
            filterResults.values=filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            filteredCoins.clear();
            filteredCoins.addAll((LinkedList<APIQuote>)filterResults.values);
            notifyDataSetChanged();
        }
    };


    //private class ItemFilter extends Filter {
        /*
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<APIQuote> list = coins;

            int count = list.size();
            final ArrayList<APIQuote> nlist = new ArrayList<>(count);

            String name;
            String ticker;

            for (int i = 0; i < count; i++) {
                //filterableString = list.get(i);
                name=list.get(i).getName().toString().toLowerCase();
                ticker=list.get(i).getSymbol().toString().toLowerCase();
                if (name.contains(filterString)) {
                    nlist.add(list.get(i));
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

 */


/*

        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = coins;
                results.count = coins.size();
            } else {
                ArrayList<APIQuote> newValues = new ArrayList<APIQuote>();
                for (APIQuote i : coins) {
                    if (i.getName().toString().toUpperCase().startsWith(constraint.toString().toUpperCase())) {
                        newValues.add(i);
                    }
                }
//MAKE SURE THAT HERE YOU GET THE PROPER VALUES IN newValues based on the constraint
// you enter
                results.values = newValues;
                results.count = newValues.size();
            }
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            //filteredCoins = (ArrayList<APIQuote>) results.values;
            //notifyDataSetChanged();
            if (results.count == 0){
                notifyDataSetInvalidated();
            } else {
                @SuppressWarnings("unchecked")
                ArrayList<APIQuote> lst = (ArrayList<APIQuote>)results.values;
                ArrayList<APIQuote> itemsList = new ArrayList<APIQuote>(lst);

                coins = itemsList;
                clear();
                for (APIQuote item : coins) {
                    add(item);
                }
            }

        }


   //}

 */



