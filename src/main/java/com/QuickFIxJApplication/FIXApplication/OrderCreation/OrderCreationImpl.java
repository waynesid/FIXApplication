package com.QuickFIxJApplication.FIXApplication.OrderCreation;/*
package com.QuickFIxJApplication.FIXApplication.OrderCreation;

import com.QuickFIxJApplication.FIXApplication.OrderCreation.Order;
import com.QuickFIxJApplication.FIXApplication.Session.FIXSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.field.*;
import quickfix.fix44.NewOrderSingle;
import quickfix.fix44.OrderCancelReplaceRequest;
import quickfix.fix44.OrderCancelRequest;

*/

import org.springframework.stereotype.Service;
import quickfix.DecimalField;
import quickfix.field.*;
import quickfix.fix44.NewOrderSingle;
import quickfix.fix44.OrderCancelReplaceRequest;
import quickfix.fix44.OrderCancelRequest;

import java.time.LocalDateTime;

/**
 * @author Wayne Sidney
 * Created on {08/05/2022}
 */

@Service
public class OrderCreationImpl implements OrderCreation {

    @Override
    public NewOrderSingle sendNewOrderRequest(Order order) {

        NewOrderSingle newOrderSingle = new NewOrderSingle();

        newOrderSingle.set(new TransactTime(LocalDateTime.now()));
        newOrderSingle.set(new ClOrdID(order.getClOrdId()));
        newOrderSingle.set(new Symbol(order.getSymbol()));
        newOrderSingle.set(new OrdType(order.getOrdType()));
        newOrderSingle.set(new Price(order.getPrice()));
        newOrderSingle.set(new Side(order.getSide()));
        newOrderSingle.set(new Side(order.getSide()));


        return newOrderSingle;
    }

    @Override
    public void sendCancelOrderRequest(OrderCancelRequest orderCancelRequest) {

    }

    @Override
    public void orderCancelReplaceRequest(OrderCancelReplaceRequest replaceRequest) {

    }
}


