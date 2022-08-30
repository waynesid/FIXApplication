package com.QuickFIxJApplication.FIXApplication.Initiator;

import com.QuickFIxJApplication.FIXApplication.Application.FixApplicationImpl;
import quickfix.*;

import java.io.InputStream;

/**
 * @author Wayne Sidney
 * Created on {06/04/2022}
 */
public class Initiator extends Thread {


    public static Connector createConnector(Application fixApplication, InputStream initiatorConfig) throws ConfigError {

        SessionSettings sessionSettings = new SessionSettings(initiatorConfig);
        MessageStoreFactory storeFactory = new FileStoreFactory(sessionSettings);
        LogFactory logFactory = new FileLogFactory(sessionSettings);

        MessageFactory messageFactory = new DefaultMessageFactory();

        return new SocketInitiator(fixApplication, storeFactory, sessionSettings, logFactory, messageFactory);

    }

    @Override
    public void run() {
        try {

            Application fixApplication = new FixApplicationImpl();
            Connector connector = createConnector(fixApplication, Initiator.class.getClassLoader().getResourceAsStream("initiator.cfg"));
            connector.start();
        } catch (ConfigError e) {
            e.printStackTrace();
        } catch (SessionNotFound e) {
            throw new RuntimeException(e);
        }
    }

    /*static Initiator open(InetSocketAddress address) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.setOption(StandardSocketOptions.TCP_NODELAY, true);
        socketChannel.configureBlocking(false);
        socketChannel.connect(address);

        return new Initiator(socketChannel);

    }*/


}
