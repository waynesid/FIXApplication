package com.QuickFIxJApplication.FIXApplication.OrderCreation;

import quickfix.fix44.NewOrderSingle;
import quickfix.fix44.OrderCancelReplaceRequest;
import quickfix.fix44.OrderCancelRequest;

/**
 * @author Wayne Sidney
 * Created on {08/05/2022}
 */
public interface OrderCreation {

    NewOrderSingle sendNewOrderRequest(Order order);

    void sendCancelOrderRequest(OrderCancelRequest orderCancelRequest);
    void orderCancelReplaceRequest(OrderCancelReplaceRequest replaceRequest);
}
