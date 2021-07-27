package com.example.coinbaseapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class AddDialogFragment extends AppCompatDialogFragment {
    private EditText addTicker;
    private EditText addAmount;
    TickerViewModel mViewModel;
    ArrayList<String> companyList;
    String key;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(requireActivity()).get(TickerViewModel.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_dialog, null);
        addTicker = view.findViewById(R.id.tickerID);
        addAmount = view.findViewById(R.id.tickerAmount);

        builder.setView(view).setTitle("Add Crytpocurrency").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int i) {

             }
        }).setPositiveButton("Add", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int i) {
                            String ticker = addTicker.getText().toString();
                            String amount = addAmount.getText().toString();
                            ticker = ticker.toUpperCase();
                            makeRequest(ticker,amount);
                     }
        });

         return builder.create();
        }

    private void makeRequest(String ticker, String Amount){
        Portfolio portfolio = new Portfolio();

        AndroidNetworking.initialize(getActivity());
        //https://financialmodelingprep.com/api/v3/quote/AAPL?apikey=demo
        ANRequest req = AndroidNetworking.get("https://financialmodelingprep.com/api/v3/quote/{ticker}")
                .addPathParameter("ticker", ticker)
                .addQueryParameter("apikey", "4c83ea6a2f1e03eb2fdb0ba3768d68fb")
                .setPriority(Priority.LOW)
                .build();
        req.getAsObjectList(APIQuote.class, new ParsedRequestListener<List<APIQuote>>() {
            @Override
            public void onResponse(List<APIQuote> company) {
                for (APIQuote profile : company) {
                        portfolio.setPrice(Float.toString(profile.getPrice()));
                        portfolio.setName(profile.getName());
                        portfolio.setTicker(profile.getSymbol());
                        portfolio.setAmount(Amount);

                        mViewModel.addTicker(portfolio);
                    }

                }
            @Override
            public void onError(ANError anError) {
                // handle error
                Toast.makeText(getActivity(),"Coin was not found", Toast.LENGTH_LONG).show();
            }
        });
    }
}


