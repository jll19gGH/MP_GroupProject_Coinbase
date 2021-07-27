package com.example.coinbaseapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;

public class TickerViewModel extends ViewModel {
    private MutableLiveData<LinkedList<Portfolio>> tickers;
    private MutableLiveData<Portfolio> currentTicker;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference portfolioList = database.getReference("/"+user.getUid()+"/portfolio");
    DatabaseReference clearList = database.getReference("/"+user.getUid());


    DatabaseReference newChildRef;
    String key;

    private MutableLiveData<String> portfolioText;

    public LiveData<String> getText() {
        if (portfolioText == null) {
            portfolioText = new MutableLiveData<>();
        }
        return portfolioText;
    }


    public void ChangePort(String p){
        String temp = p;
        portfolioText.setValue(temp);
    }

    public LiveData<LinkedList<Portfolio>> getTickers() {
        if (tickers == null) {
            tickers = new MutableLiveData<>();
        }
        return tickers;
    }

    public void setCurrentTicker(Portfolio ticker) {
        if (currentTicker == null) {
            currentTicker = new MutableLiveData<Portfolio>();
        }
        currentTicker.setValue(ticker);
    }

    public void setTickers(LinkedList<Portfolio> newTickers) {
        if (tickers == null) {
            tickers = new MutableLiveData<>();
        }
        tickers.setValue(newTickers);
    }

    public void addTicker(Portfolio t){
        LinkedList<Portfolio> tempList = tickers.getValue();
        boolean isFound = false;
        assert tempList != null;
        for(int i = 0; i<tempList.size(); i++)
        {
            if(tempList.get(i).getCryptoName().equals(t.getCryptoName())) {
                tempList.get(i).setAmount(Double.toString(
                        Double.parseDouble(tempList.get(i).getAmount()) +
                                Double.parseDouble(t.getAmount())));
                newChildRef = portfolioList.push();
                portfolioList.child(t.getCryptoName()).removeValue();
                portfolioList.child(t.getCryptoName()).setValue(tempList.get(i));
                isFound = true;
            }
        }
        if(isFound == false) {
            newChildRef = portfolioList.push();
            portfolioList.child(t.getCryptoName()).setValue(t);
            tempList.add(t);
        }
        tickers.setValue(tempList);
    }

    public void setList(Portfolio t){
        LinkedList<Portfolio> tempList = tickers.getValue();
        assert tempList != null;
        tempList.add(t);
        tickers.setValue(tempList);
    }

    public void clearList(){
        LinkedList<Portfolio> tempList = tickers.getValue();
        tempList.clear();
        tickers.setValue(tempList);
        newChildRef = clearList.push();
        clearList.child("/portfolio").removeValue();
    }

    public float getCryptoAmount(int index){
        LinkedList<Portfolio> tempList = tickers.getValue();
            return Float.parseFloat(tempList.get(index).getPrice()) * Float.parseFloat(tempList.get(index).getAmount());
    }

    public int size(){
        LinkedList<Portfolio> tempList = tickers.getValue();
        return tempList.size();
    }

    public String getName(int i){
        LinkedList<Portfolio> tempList = tickers.getValue();
        if(tickers != null)
            return tempList.get(i).getCryptoName();

        return "name";
    }

}
