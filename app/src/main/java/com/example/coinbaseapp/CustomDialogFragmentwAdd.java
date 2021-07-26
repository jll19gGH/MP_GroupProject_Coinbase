package com.example.coinbaseapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedList;


public class CustomDialogFragmentwAdd extends DialogFragment {

    APIQuote quote;
    private SharedViewModel sharedViewModel;
    //private SearchFragment sf;

    public CustomDialogFragmentwAdd(APIQuote quote) {
        this.quote = quote;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        //sf=new SearchFragment();
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        //loadWatchlist();
        //loadCoinlist();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View root = inflater.inflate(R.layout.custom_dialog, null);

        TextView textViewCompany = (TextView) root.findViewById(R.id.dialogCompanyName);
        TextView textViewTicker = (TextView) root.findViewById(R.id.dialogTickerName);
        TextView textViewPrice = (TextView) root.findViewById(R.id.dialogPrice);
        TextView textViewChangeValue = (TextView) root.findViewById(R.id.dialogChange);


        TextView textViewChangePercent = (TextView) root.findViewById(R.id.dialogChangesPercent);
        TextView textViewDayHigh = (TextView) root.findViewById(R.id.dialogDayHigh);
        TextView textViewDayLow = (TextView) root.findViewById(R.id.dialogDayLow);
        TextView textViewYearHigh = (TextView) root.findViewById(R.id.dialogYearHigh);

        TextView textViewYearLow = (TextView) root.findViewById(R.id.dialogYearLow);
        TextView textViewMktCap = (TextView) root.findViewById(R.id.dialogMktCap);
        TextView textViewVolume = (TextView) root.findViewById(R.id.dialogVolume);
        TextView textViewAvgVolume = (TextView) root.findViewById(R.id.dialogAvgVolume);



        ImageView image = (ImageView) root.findViewById(R.id.dialogTickerImage);


        String name = quote.getName();
        name = name.substring(0, name.length() - 3);
        name=name.trim();

        textViewCompany.setText(name);

        String ticker = quote.getSymbol();
        ticker = ticker.substring(0, ticker.length() - 3);

        textViewTicker.setText(ticker);
        Float price = quote.getPrice();
        NumberFormat priceFormat = new DecimalFormat("0.00");
        String priceFormatted = priceFormat.format(price);
        textViewPrice.setText("Price: $ " + priceFormatted);
        Float priceChange = quote.getChange();
        NumberFormat priceChangeFormat = new DecimalFormat("0.00");
        String priceChangeFormatted = priceChangeFormat.format(priceChange);
        textViewChangeValue.setText("Price Change: $ " + priceChangeFormatted);
        Float changePercent = quote.getChangesPercentage();
        NumberFormat changeFormat = new DecimalFormat("0.0000");
        String changeFormatted = changeFormat.format(changePercent);
        textViewChangePercent.setText("Change Percentage: "+changeFormatted + " %");

        Float dayHigh = quote.getDayHigh();
        NumberFormat dayHighFormat = new DecimalFormat("0.00");
        String dayHighFormatted = dayHighFormat.format(dayHigh);
        textViewDayHigh.setText("Day High: $ " + dayHighFormatted);

        Float dayLow = quote.getDayLow();
        NumberFormat dayLowFormat = new DecimalFormat("0.00");
        String dayLowFormatted = dayLowFormat.format(dayLow);
        textViewDayLow.setText("Day Low: $ " + dayLowFormatted);

        Float yearHigh = quote.getYearHigh();
        NumberFormat YearHighFormat = new DecimalFormat("0.00");
        String YearHighFormatted = YearHighFormat.format(yearHigh);
        textViewYearHigh.setText("Year High: $ " + YearHighFormatted);

        Float yearLow = quote.getYearLow();
        NumberFormat YearLowFormat = new DecimalFormat("0.00");
        String YearLowFormatted = YearLowFormat.format(yearLow);
        textViewYearLow.setText("Year Low: $ " + YearLowFormatted);

        Float mktcap = quote.getMarketCap();
        NumberFormat mktcapFormat = new DecimalFormat("0.00");
        String mktcapFormatted = mktcapFormat.format(mktcap);
        textViewMktCap.setText("Market Cap: $ " + mktcapFormatted);

        Float volume = quote.getVolume();
        NumberFormat volumeFormat = new DecimalFormat("0.00");
        String volumeFormatted = volumeFormat.format(volume);
        textViewVolume.setText("Volume: $ " + volumeFormatted);

        Float avgvol = quote.getAvgVolume();
        NumberFormat avgvolFormat = new DecimalFormat("0.00");
        String avgvolFormatted = avgvolFormat.format(avgvol);
        textViewAvgVolume.setText("Average Volume: $ " + avgvolFormatted);

        String imageURL = "https://cryptologos.cc/logos/usd-coin-usdc-logo.png?v=013";

        switch(name){
                case "Siacoin":
                    imageURL = "https://cryptologos.cc/logos/siacoin-sc-logo.png?v=013";
                    break;
                case "Tuesday":
                case "Wednesday":
                case "Thursday":
                    //typeOfDay = "Midweek";
                    break;
                case "Friday":
                    //typeOfDay = "End of work week";
                    break;
                case "Saturday":
                case "Sunday":
                    //typeOfDay = "Weekend";
                    break;
                default:
        }

        Picasso.get().load(imageURL).fit().into(image);


        builder.setView(root)
                // Add action buttons
                .setNegativeButton("Add to watchlist", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
//Toast.makeText(getActivity(),quote.getName(),Toast.LENGTH_LONG).show();

                        //APIQuote coin=new APIQuote(quote.getSymbol(), quote.getName(), quote.getPrice(), quote.getChangesPercentage(), quote.getChange(), quote.getDayLow(), quote.getDayHigh(), quote.getYearHigh(), quote.getYearLow(), quote.getMarketCap(), quote.getPriceAvg50(), quote.getPriceAvg200(), quote.getVolume(), quote.getAvgVolume(), quote.getExchange(), quote.getOpen(), quote.getPreviousClose(), quote.getEps(), quote.getPe(), quote.getEarningsAnnouncement(), quote.getSharesOutstanding(), quote.getTimestamp());


                        //sharedViewModel.addToWatchlist(coin);

                        //sharedViewModel.setCurrentCoin(quote);

                        //sf.add();
                        //sharedViewModel.addToWatchlist(sharedViewModel.getCurrentCoin().getValue());
SearchFragment sf=(SearchFragment) getParentFragment();
sf.addtoWatchlist.setValue(true);
                    }
                })
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }
    /*
    private void loadWatchlist() {
        LinkedList<APIQuote> tempList=new LinkedList<>();
        sharedViewModel.setWatchlist(tempList);
    }

    private void loadCoinlist() {
        LinkedList<APIQuote> tempList=new LinkedList<>();
        sharedViewModel.setCoinlist(tempList);
    }

     */

}
