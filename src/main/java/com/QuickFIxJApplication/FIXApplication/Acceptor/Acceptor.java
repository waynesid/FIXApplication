package com.QuickFIxJApplication.FIXApplication.Acceptor;

import com.QuickFIxJApplication.FIXApplication.Application.FixApplicationImpl;
import quickfix.*;

import java.io.InputStream;


/**
 * @author Wayne Sidney
 * Created on {11/04/2022}
 */
public class Acceptor extends Thread {

    @Override
    public void run() {
        try {
            Application fixApplication = new FixApplicationImpl();
            Connector connector = createConnector(fixApplication, Acceptor.class.getClassLoader().getResourceAsStream("acceptor.cfg"));
            connector.start();
        } catch (ConfigError e) {
            e.printStackTrace();
        } catch (SessionNotFound e) {
            throw new RuntimeException(e);
        }

    }

    public static Connector createConnector(Application fixApplication, InputStream acceptorConfig) throws ConfigError {

        SessionSettings sessionSettings = new SessionSettings(acceptorConfig);
        MessageStoreFactory storeFactory = new FileStoreFactory(sessionSettings);
        LogFactory logFactory = new FileLogFactory(sessionSettings);

        MessageFactory messageFactory = new DefaultMessageFactory();

        return new SocketAcceptor(fixApplication, storeFactory, sessionSettings, logFactory, messageFactory);

    }

}
