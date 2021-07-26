package com.example.coinbaseapp;

public class APIQuote implements Cloneable {
    private String symbol;
    private String name;
    private Float price;
    private Float changesPercentage;
    private Float change;
    private Float dayLow;
    private Float dayHigh;
    private Float yearHigh;
    private Float yearLow;
    private Float marketCap;
    private Float priceAvg50;
    private Float priceAvg200;
    private Float volume;
    private Float avgVolume;
    private String exchange;
    private Float open;
    private Float previousClose;
    private String eps;
    private String pe;
    private String earningsAnnouncement;
    private Float sharesOutstanding;
    private Float timestamp;
    private String imageURL;

    public APIQuote() {
        //default constructor needed for Datasnapshot
    }

    public APIQuote(String symbol,String name,Float price,Float changesPercentage,Float change,Float dayLow,Float dayHigh, Float yearHigh,Float yearLow,
             Float marketCap,
             Float priceAvg50,
             Float priceAvg200,
             Float volume,
             Float avgVolume,
             String exchange,
             Float open,
             Float previousClose,
             String eps,
             String pe,
             String earningsAnnouncement,
             Float sharesOutstanding,
             Float timestamp) {
        this.symbol=symbol;
        this.name=name;
        this.price=price;
        this.changesPercentage=changesPercentage;
        this.change=change;
        this.dayLow=dayLow;
        this.dayHigh=dayHigh;
        this.yearHigh=yearHigh;
        this.yearLow=yearLow;
        this.marketCap=marketCap;
        this.priceAvg50=priceAvg50;
        this.priceAvg200=priceAvg200;
        this.volume=volume;
        this.avgVolume=avgVolume;
        this.exchange=exchange;
        this.open=open;
        this.previousClose=previousClose;
        this.eps=eps;
        this.pe=pe;
        this.earningsAnnouncement=earningsAnnouncement;
        this.sharesOutstanding=sharesOutstanding;
        this.timestamp=timestamp;


    }


    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {return symbol;}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {return name;}
    public Float getPrice(){return price;}
    public Float getChangesPercentage(){return changesPercentage;}
    public Float getChange(){return change;}
    public Float getDayLow(){return dayLow;}
    public Float getDayHigh(){return dayHigh;}
    public Float getYearHigh(){return yearHigh;}
    public Float getYearLow(){return yearLow;}
    public Float getMarketCap(){return marketCap;}
    public Float getPriceAvg50(){return priceAvg50;}
    public Float getPriceAvg200(){return priceAvg200;}
    public Float getVolume(){return volume;}
    public Float getAvgVolume(){return avgVolume;}
    public String getExchange(){return exchange;}
    public Float getOpen(){return open;}
    public Float getPreviousClose(){return previousClose;}
    public String getEps(){return eps;}
    public String getPe(){return pe;}
    public String getEarningsAnnouncement(){return earningsAnnouncement;}
    public Float getSharesOutstanding(){return sharesOutstanding;}
    public Float getTimestamp(){return timestamp;}

    @Override
    public String toString() {
        return  name + " " + symbol + " ";
    }

}
