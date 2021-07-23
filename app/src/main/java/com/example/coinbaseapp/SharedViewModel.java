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
    public MutableLiveData<LinkedList<APIQuote>> watchlist;
    //public MutableLiveData<LinkedList<APIQuote>> APIQuotes=new MutableLiveData<>();
/*
    public void setAPIQuotes(LinkedList<APIQuote> quotes) {
        if (APIQuotes == null) {
            APIQuotes = new MutableLiveData<>();
        }
        APIQuotes.setValue(quotes);
    }

    public void addAPIQuote(APIQuote quote){
        LinkedList<APIQuote> tempList = APIQuotes.getValue();
        assert tempList != null;
        tempList.add(quote);
        APIQuotes.setValue(tempList);
    }

    public LiveData<LinkedList<APIQuote>> getAPIQuotes() {
        if (APIQuotes == null) {
            APIQuotes = new MutableLiveData<>();
        }
        return APIQuotes;
    }

 */

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

    public void removeFromWatchlist(APIQuote quote)
    {
        LinkedList<APIQuote> tempList = watchlist.getValue();
        assert tempList != null;
        tempList.remove(quote);
        watchlist.setValue(tempList);
    }

    public LiveData<LinkedList<APIQuote>> getWatchList() {
        if (watchlist == null) {
            watchlist = new MutableLiveData<>();
        }
        return watchlist;
    }

}
