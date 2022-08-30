package com.QuickFIxJApplication.FIXApplication.OrderCreation;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Wayne Sidney
 * Created on {07/05/2022}
 */
public class OrderTIF {

    static private final Map known = new HashMap();

    static public final OrderTIF DAY = new OrderTIF("Day");
    static public final OrderTIF IOC = new OrderTIF("IOC");
    static public final OrderTIF OPG = new OrderTIF("OPG");
    static public final OrderTIF GTC = new OrderTIF("GTC");
    static public final OrderTIF GTX = new OrderTIF("GTX");

    static private final OrderTIF[] array = {DAY, IOC, OPG, GTC, GTX};

    private final String name;

    private OrderTIF(String name) {
        this.name = name;
        synchronized (OrderTIF.class) {
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

    public static OrderTIF parse(String type)
            throws IllegalArgumentException {
        OrderTIF result = (OrderTIF) known.get(type);
        if (result == null) {
            throw new IllegalArgumentException
                    ("OrderTIF:  " + type + " is unknown.");
        }
        return result;
    }

}
