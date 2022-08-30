package com.QuickFIxJApplication.FIXApplication;

/**
 * @author Wayne Sidney
 * Created on {01/06/2022}
 */

public class MarketDataRequest {

    //unique id for this request .
    // Acceptor returns this ID in all responses to the request
    private String MDReqID;

  /*
    0-snapshot
    1-snapshot plus updates(subscribe)
    2-snapshot(unsubscribe)*/
    private char SubscriptionRequestType;


    //type pf market data to request
    private char MDEntryType;


}
