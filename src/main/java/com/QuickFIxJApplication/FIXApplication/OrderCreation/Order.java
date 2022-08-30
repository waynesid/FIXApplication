package com.QuickFIxJApplication.FIXApplication.OrderCreation;

import com.QuickFIxJApplication.FIXApplication.OrderCreation.OrderTIF;
import quickfix.SessionID;

/**
 * @author Wayne Sidney
 * Created on {21/04/2022}
 */

public class Order {



    private String clOrdId;
    private char side;
    private float price;
    private float qty;
    private char tif;
    private char ordType;
    private String symbol;
    private char ordStatus;


    public Order() {
    }

    public Order(String clOrdId, char side, float price, float qty, char tif, char ordType, String symbol, char ordStatus) {
        this.clOrdId = clOrdId;
        this.side = side;
        this.price = price;
        this.qty = qty;
        this.tif = tif;
        this.ordType = ordType;
        this.symbol = symbol;
        this.ordStatus = ordStatus;
    }

    public String getClOrdId() {
        return clOrdId;
    }

    public void setClOrdId(String clOrdId) {
        this.clOrdId = clOrdId;
    }

    public char getSide() {
        return side;
    }

    public void setSide(char side) {
        this.side = side;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    public char getTif() {
        return tif;
    }

    public void setTif(char tif) {
        this.tif = tif;
    }

    public char getOrdType() {
        return ordType;
    }

    public void setOrdType(char ordType) {
        this.ordType = ordType;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public char getOrdStatus() {
        return ordStatus;
    }

    public void setOrdStatus(char ordStatus) {
        this.ordStatus = ordStatus;
    }

}