package com.QuickFIxJApplication.FIXApplication.OrderCreation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wayne Sidney
 * Created on {21/04/2022}
 */
public class OrderSide {

    private static final Map<String, OrderSide> known = new HashMap<>();

    public static final OrderSide BUY = new OrderSide("Buy");

    public static final OrderSide SELL = new OrderSide("Sell");

    public static final OrderSide SHORT_SELL = new OrderSide("Short-Sell");

    public static final OrderSide CROSS = new OrderSide("Cross");


    private static final OrderSide[] array = {BUY, SELL, SHORT_SELL, CROSS};

    private final String name;


    public OrderSide(String name) {
        this.name = name;
        synchronized (OrderSide.class) {
            known.put(name, this);
        }

    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    static public Object[] toArray() {
        return array;
    }

}
