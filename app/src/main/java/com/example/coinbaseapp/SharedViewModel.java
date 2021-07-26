package com.example.coinbaseapp;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;

import java.util.LinkedList;

public class SharedViewModel extends ViewModel {
    public MutableLiveData<APIQuote> currentCoin;
    public MutableLiveData<LinkedList<APIQuote>> watchlist;
    public MutableLiveData<LinkedList<APIQuote>> coinlist;
    public MutableLiveData<LinkedList<APIQuote>> gainers;
    public MutableLiveData<LinkedList<APIQuote>> losers;


    public LiveData<APIQuote> getCurrentCoin() {
        if (currentCoin == null) {
            currentCoin = new MutableLiveData<>();
            currentCoin.postValue(new APIQuote());
        }
        return currentCoin;
    }

    public void setCurrentCoin(APIQuote coin) {
        if (currentCoin == null) {
            currentCoin = new MutableLiveData<APIQuote>();
        }
        currentCoin.setValue(coin);
    }

    public void setWatchlist(LinkedList<APIQuote> quotes) {
        if (watchlist == null) {
            watchlist = new MutableLiveData<>();
        }
        watchlist.setValue(quotes);
    }

    public void addToWatchlist(APIQuote quote) {
        LinkedList<APIQuote> tempList = watchlist.getValue();
        assert tempList != null;
        tempList.add(quote);
        watchlist.setValue(tempList);
    }

    public LiveData<LinkedList<APIQuote>> getWatchList() {
        if (watchlist == null) {
            watchlist = new MutableLiveData<>();
        }
        return watchlist;
    }

    //---------------------------------------------------------------

    public void setCoinlist(LinkedList<APIQuote> quotes) {
        if (coinlist == null) {
            coinlist = new MutableLiveData<>();
        }
        coinlist.setValue(quotes);
    }

    public void addToCoinlist(APIQuote quote) {
        LinkedList<APIQuote> tempList = coinlist.getValue();
        assert tempList != null;
        tempList.add(quote);
        coinlist.setValue(tempList);
    }

    public LiveData<LinkedList<APIQuote>> getCoinlist() {
        if (coinlist == null) {
            coinlist = new MutableLiveData<>();
        }
        return coinlist;
    }

    //---------------------------------------

    public void setGainers(LinkedList<APIQuote> quotes) {
        if (gainers == null) {
            gainers = new MutableLiveData<>();
        }
        gainers.setValue(quotes);
    }

    public void addToGainers(APIQuote quote) {
        LinkedList<APIQuote> tempList = gainers.getValue();
        assert tempList != null;

        if(tempList.size()<=4)
       {
            tempList.add(quote);
        }
        gainers.setValue(tempList);
    }

    public LiveData<LinkedList<APIQuote>> getGainers() {
        if (gainers == null) {
            gainers = new MutableLiveData<>();
        }
        return gainers;
    }

    //---------------------------------------

    public void setLosers(LinkedList<APIQuote> quotes) {
        if (losers == null) {
            losers = new MutableLiveData<>();
        }
        losers.setValue(quotes);
    }

    public void addToLosers(APIQuote quote) {
        LinkedList<APIQuote> tempList = losers.getValue();
        assert tempList != null;

        if(tempList.size()<=4)
        {
            tempList.add(quote);
        }
        losers.setValue(tempList);
    }

    public LiveData<LinkedList<APIQuote>> getLosers() {
        if (losers == null) {
            losers = new MutableLiveData<>();
        }
        return losers;
    }

}
