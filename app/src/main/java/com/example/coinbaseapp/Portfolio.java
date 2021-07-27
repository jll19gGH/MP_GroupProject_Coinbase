package com.example.coinbaseapp;

public class Portfolio {
    private String Amount;
    private String CryptoName;
    private String Price;
    private String CryptoTicker;

    public void Portfolio(String amount, String name, String price, String ticker){
        this.Amount = amount;
        this.CryptoName = name;
        this.Price = price;
        this.CryptoTicker = ticker;
    }

    public void setAmount(String amount)
    {
        this.Amount = amount;
    }

    public void setName(String crytpoName) {
        this.CryptoName = crytpoName;
    }

    public void setPrice(String price){
        this.Price = price;
    }

    public void setTicker(String ticker){
        this.CryptoTicker = ticker;
    }

    public String getAmount(){
        return Amount;
    }

    public String getCryptoName(){
        return CryptoName;
    }

    public String getPrice(){
        return Price;
    }

    public String getCrytpoTicker(){
        return CryptoTicker;
    }
}
