package com.example.coinbaseapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class CustomDialogFragmentNoAdd extends DialogFragment {

    APIQuote quote;

    public CustomDialogFragmentNoAdd(APIQuote quote) {
        this.quote = quote;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        final View root = inflater.inflate(R.layout.fragment_custom_dialog_no_add, null);

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
        name = name.trim();

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
        textViewChangePercent.setText("Change Percentage: " + changeFormatted + " %");

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

        String imageURL = "https://upload.wikimedia.org/wikipedia/commons/4/48/BLANK_ICON.png";

        switch (name) {
            case "Siacoin":
                imageURL = "https://cryptologos.cc/logos/siacoin-sc-logo.png?v=013";
                break;
            case "Gnosis":
                imageURL = "https://cryptologos.cc/logos/gnosis-gno-gno-logo.png?v=013";
                break;
            case "EOS":
                imageURL = "https://cryptologos.cc/logos/eos-eos-logo.png?v=013";
                break;
            case "NoLimitCoin":
                imageURL = "https://cryptologos.cc/logos/nolimitcoin-nlc2-logo.png?v=013";
                break;
            case "Exchange Union":
                imageURL = "https://cryptologos.cc/logos/exchange-union-xuc-logo.png?v=013";
                break;


            case "MCO":
                imageURL = "https://cryptologos.cc/logos/crypto-com-mco-logo.png?v=013";
                break;
            case "Blocknet":
                imageURL = "https://cryptologos.cc/logos/blocknet-block-logo.png?v=013";
                break;
            case "Loopring":
                imageURL = "https://cryptologos.cc/logos/loopring-lrc-logo.png?v=013";
                break;
            case "Litecoin":
                imageURL = "https://cryptologos.cc/logos/litecoin-ltc-logo.png?v=013";
                break;


            case "Aragon":
                imageURL = "https://cryptologos.cc/logos/aragon-ant-logo.png?v=013";
                break;
            case "Obyte":
                imageURL = "https://cryptologos.cc/logos/obyte-gbyte-logo.png?v=013";
                break;
            case "GameCredits":
                imageURL = "https://cryptologos.cc/logos/gamecredits-game-logo.png?v=013";
                break;
            case "NEM":
                imageURL = "https://cryptologos.cc/logos/nem-xem-logo.png?v=013";
                break;
            case "Factom":
                imageURL = "https://cryptologos.cc/logos/factom-fct-logo.png?v=013";
                break;
            case "Gas":
                imageURL = "https://cryptologos.cc/logos/gas-gas-logo.png?v=013";
                break;
            case "SmartCash":
                imageURL = "https://cryptologos.cc/logos/smartcash-smart-logo.png?v=013";
                break;


            case "Verge":
                imageURL = "https://cryptologos.cc/logos/verge-xvg-logo.png?v=013";
                break;
            case "Waltonchain":
                imageURL = "https://cryptologos.cc/logos/waltonchain-wtc-logo.png?v=013";
                break;
            case "Populous":
                imageURL = "https://cryptologos.cc/logos/populous-ppt-logo.png?v=013";
                break;
            case "Lykke":
                imageURL = "https://cryptologos.cc/logos/lykke-lkk-logo.png?v=013";
                break;
            case "Substratum":
                imageURL = "https://cryptologos.cc/logos/substratum-sub-logo.png?v=013";
                break;
            case "Neblio":
                imageURL = "https://cryptologos.cc/logos/neblio-nebl-logo.png?v=013";
                break;


            case "Stellar":
                imageURL = "https://cryptologos.cc/logos/stellar-xlm-logo.png?v=013";
                break;
            case "Dash":
                imageURL = "https://cryptologos.cc/logos/dash-dash-logo.png?v=013";
                break;
            case "Decred":
                imageURL = "https://cryptologos.cc/logos/decred-dcr-logo.png?v=013";
                break;
            case "Ethereum":
                imageURL = "https://cryptologos.cc/logos/ethereum-eth-logo.png?v=013";
                break;


            case "Basic Attention Token":
                imageURL = "https://cryptologos.cc/logos/basic-attention-token-bat-logo.png?v=013";
                break;
            case "TenX":
                imageURL = "https://cryptologos.cc/logos/tenx-pay-logo.png?v=013";
                break;


            case "Bitcoin Cash":
                imageURL = "https://cryptologos.cc/logos/bitcoin-cash-bch-logo.png?v=013";
                break;
            case "Bitcoin Gold":
                imageURL = "https://cryptologos.cc/logos/bitcoin-gold-btg-logo.png?v=013";
                break;
            case "ICON":
                imageURL = "https://cryptologos.cc/logos/icon-icx-logo.png?v=013";
                break;
            case "NavCoin":
                imageURL = "https://cryptologos.cc/logos/nav-coin-nav-logo.png?v=013";
                break;
            case "Civic":
                imageURL = "https://cryptologos.cc/logos/civic-cvc-logo.png?v=013";
                break;
            case "BitShares":
                imageURL = "https://cryptologos.cc/logos/bitshares-bts-logo.png?v=013";
                break;


            case "Veritaseum":
                imageURL = "https://cryptologos.cc/logos/veritaseum-veri-logo.png?v=013";
                break;
            case "Status":
                imageURL = "https://cryptologos.cc/logos/status-snt-logo.png?v=013";
                break;
            case "Bancor":
                imageURL = "https://cryptologos.cc/logos/bancor-bnt-logo.png?v=013";
                break;
            case "Lisk":
                imageURL = "https://cryptologos.cc/logos/lisk-lsk-logo.png?v=013";
                break;
            case "SingularDTV":
                imageURL = "https://cryptologos.cc/logos/singulardtv-sngls-logo.png?v=013";
                break;


            case "Nexus":
                imageURL = "https://cryptologos.cc/logos/nexus-nxs-logo.png?v=013";
                break;
            case "Ardor":
                imageURL = "https://cryptologos.cc/logos/ardor-ardr-logo.png?v=013";
                break;
            case "Komodo":
                imageURL = "https://cryptologos.cc/logos/komodo-kmd-logo.png?v=013";
                break;
            case "DigixDAO":
                imageURL = "https://cryptologos.cc/logos/digixdao-dgd-logo.png?v=013";
                break;


            case "QASH":
                imageURL = "https://cryptologos.cc/logos/qash-qash-logo.png?v=013";
                break;
            case "Binance Coin":
                imageURL = "https://cryptologos.cc/logos/binance-coin-bnb-logo.png?v=013";
                break;
            case "Dogecoin":
                imageURL = "https://cryptologos.cc/logos/dogecoin-doge-logo.png?v=013";
                break;
            case "Ethereum Classic":
                imageURL = "https://cryptologos.cc/logos/ethereum-classic-etc-logo.png?v=013";
                break;
            case "PIVX":
                imageURL = "https://cryptologos.cc/logos/pivx-pivx-logo.png?v=013";
                break;

            case "Bitcoin":
                imageURL = "https://cryptologos.cc/logos/bitcoin-btc-logo.png?v=013";
                break;


            default:
                imageURL = "https://upload.wikimedia.org/wikipedia/commons/4/48/BLANK_ICON.png";
        }


        Picasso.get().load(imageURL).fit().into(image);


        builder.setView(root)
                // Add action buttons
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }

}