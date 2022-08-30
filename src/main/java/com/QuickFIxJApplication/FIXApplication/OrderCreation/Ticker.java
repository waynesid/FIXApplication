package com.QuickFIxJApplication.FIXApplication.OrderCreation;

/**
 * @author Wayne Sidney
 * Created on {28/06/2022}
 */
public class Ticker {

    private String volume;
    private String symbol;
    private String high;
    private String last;
    private String low;
    private String volumeQuote;
    private String ask;
    private String bid;
    private String open;
    private String timeStamp;

    public Ticker(String volume, String symbol, String high, String last, String low, String volumeQuote, String ask, String bid, String open, String timeStamp) {
        this.volume = volume;
        this.symbol = symbol;
        this.high = high;
        this.last = last;
        this.low = low;
        this.volumeQuote = volumeQuote;
        this.ask = ask;
        this.bid = bid;
        this.open = open;
        this.timeStamp = timeStamp;
    }
}
