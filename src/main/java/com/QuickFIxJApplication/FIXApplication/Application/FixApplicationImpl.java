package com.QuickFIxJApplication.FIXApplication.Application;

import com.paritytrading.philadelphia.coinbase.Coinbase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.extern.slf4j.Slf4j;
import quickfix.*;
import quickfix.field.*;
import quickfix.fix44.Logon;
import quickfix.fix44.MarketDataRequest;
import quickfix.fix44.QuoteRequest;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @author Wayne Sidney
 * Created on {06/04/2022}
 */


@Slf4j
public class FixApplicationImpl extends MessageCracker implements Application {

    private static final Logger LOGGER = Logger.getLogger(String.valueOf(FixApplicationImpl.class));

    private static Session defaultSession;

    private SessionSettings sessionSettings;

    String uuid = java.util.UUID.randomUUID().toString();

    public FixApplicationImpl() throws SessionNotFound {

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            sendCoinbaseLogon();


        }, 10, 10, TimeUnit.SECONDS);

    }

    private void sendCoinbaseLogon() {
    }

    @Override
    public void onCreate(SessionID sessionID) {

         defaultSession = Session.lookupSession(sessionID);
         defaultSession.logon();
         defaultSession.isLoggedOn();

         LOGGER.info("-----SESSION CREATED----" + sessionID.toString());
    }

    @Override
    public void onLogon(SessionID sessionID) {

        LOGGER.info("------SESSION IS LOGGED ON -------" + sessionID.toString());

    }

    @Override
    public void onLogout(SessionID sessionID) {


        LOGGER.info("------logout ---" + sessionID.toString());

    }

    @Override
    public void toAdmin(Message message, SessionID sessionID) {

        try {
            MsgType msgType = new MsgType();
            message.getHeader().getField(msgType);

            if (msgType.valueEquals(MsgType.LOGON)) {

                message.setField(new Password("9mdk7z8lite"));
                message.setField(new EncryptMethod(0));
                message.setField(new RawData("message signature"));

                sendCoinbaseLogon();

                String key = "8e7d07b3dfb6bbf82364ca0bdaa0913e";
                String secret = "Zu8x46TRsxETSOmIdPpejSva+n9vWg9M6XjUuI472X1W1beo5f1fVCLWZdJG/8L/AZnk8G7FjsfpTTGWO5JNMw==";

                //Coinbase.sign(message, secret);

            } else if (msgType.valueEquals(MsgType.HEARTBEAT)) {
                LOGGER.info("---heartbeat---");

            }
        } catch (FieldNotFound e) {
            throw new RuntimeException(e);
        }

        log.info("TOADMIN");
    }

    @Override
    public void fromAdmin(Message message, SessionID sessionID) {


    }

    @Override
    public void toApp(Message message, SessionID sessionID) {

        MsgType msgType = new MsgType();
        try {
            message.getHeader().getField(msgType);
        } catch (FieldNotFound e) {
            throw new RuntimeException(e);
        }
        log.info("------Initiator to Acceptor: msgType: " + msgType.getValue() );
    }

    @Override
    public void fromApp(Message message, SessionID sessionID)
            throws FieldNotFound {

        //get incoming message types
        MsgType msgType = new MsgType();
        message.getHeader().getField(msgType);

        //sendingTime of the message
        String sendingTimeStr = message.getHeader().getString(SendingTime.FIELD);

        LOGGER.info("FromApp : sendingTime: " + sendingTimeStr + " | msgType: " + msgType.getValue());

        if (msgType.valueEquals(MsgType.QUOTE_REQUEST)) {
            LOGGER.info("*****Msgtype*****: " + msgType.getValue());
        } else if (msgType.valueEquals(MsgType.MARKET_DATA_REQUEST)) {
            LOGGER.info("******MsgType*****: " + msgType.getValue());

        }


        try {
            if (message.isSetField(Currency.FIELD)) {
                LOGGER.info("currency field set");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private boolean isLoggedOn() {

        return defaultSession != null;
    }

    private void sendHelloWorld() {
        if (isLoggedOn()) {
            QuoteRequest quoteRequest = new QuoteRequest();
            quoteRequest.setString(Text.FIELD, "Hello World");
            defaultSession.send(quoteRequest);
        } else {
            LOGGER.info("session is not logged in, message will not send ");
        }

    }

    public void newSingleOrder() throws SessionNotFound {

        quickfix.fix44.NewOrderSingle message = new quickfix.fix44.NewOrderSingle();

        message.setField(new OrdType(OrdType.MARKET));
        message.setField(new Text("Sell google stocks"));
        message.setField(new Symbol("GOOGL"));
        message.setField(new Side(Side.SELL));

        defaultSession.send(message);
        LOGGER.info("SendingOrder: " +message.toString() );

    }

    public void newSingleOrder2() throws SessionNotFound, FieldNotFound {

        quickfix.fix44.NewOrderSingle message = new quickfix.fix44.NewOrderSingle(
                new ClOrdID(uuid),
                new Side(Side.BUY),
                new TransactTime(LocalDateTime.now()),
                new OrdType(OrdType.MARKET)
        );

        message.setField(new Text("buy Tesla stocks"));
        message.setField(new Symbol("TSLA"));
        message.setField(new Currency("USD"));
        message.setField(new OrderQty(1200));

        defaultSession.send(message);

        LOGGER.info("SendingOrder: " + message);

    }

    public static void sendLogonRequest(SessionID sessionId) throws SessionNotFound {
        quickfix.fix44.Logon logon = new quickfix.fix44.Logon();
        quickfix.fix44.Message.Header header = (quickfix.fix44.Message.Header) logon.getHeader();

        boolean sent = Session.sendToTarget(logon, sessionId);

        Config config =     ConfigFactory.parseResources("initiator.conf");
        String address = config.getString("coinbase.fix.address");
        int port    = config.getInt("coinbase.fix.port");

        String passphrase = config.getString("coinbase.api.passphrase");
        String key        = config.getString("coinbase.api.key");
        String secret     = config.getString("coinbase.api.secret");


        //Coinbase.sign(message, secret);


        System.out.println("logon message sent: " + sent);

    }

    public static void sendMarketDataRequest() {

        Message message = new Message();
        MarketDataRequest.NoMDEntryTypes group = new MarketDataRequest.NoMDEntryTypes();
        MarketDataRequest.NoRelatedSym group1 = new MarketDataRequest.NoRelatedSym();

        MarketDataRequest marketDataRequest = new MarketDataRequest();

        quickfix.fix44.Message.Header header = (quickfix.fix44.Message.Header) marketDataRequest.getHeader();
        header.setField(new BeginString("FIX.4.4"));
        header.setField(new SenderCompID("ATHENA"));
        header.setField(new TargetCompID("ACCEPTOR"));
        header.setField(new MsgType("V"));
        header.setField(new MDReqID());
        message.setField(new SubscriptionRequestType((char) 1));
        message.setField(new MarketDepth(1));
        message.setField(new NoMDEntryTypes(1));
        group.setField(new MDEntryType((char) 1));
        message.addGroup(group);
        group1.setField(new Symbol("ALL"));
        message.addGroup(group1);

        try
        {
            defaultSession.send(marketDataRequest);
            System.out.println("message " + message);
        }catch (Exception e)
        {
            System.out.println("error" + e);
        }


    }

   /* public void onMessage(ExecutionReport executionReport, SessionID sessionID) throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue{
        System.out.println("New Execution Report for " + executionReport.getClOrdID().getValue() + ", status " + executionReport.getOrdStatus().getValue());
        System.out.println(executionReport.toString());
        Order order = OrderStore.findOrder(executionReport.getClientID().getValue());
        if(order != null){
            order.setOrdStatus(executionReport.getOrdStatus().getValue());
        }
    }*/

}
