/*
package com.QuickFIxJApplication.FIXApplication.Session;

import com.QuickFIxJApplication.FIXApplication.Initiator.Initiator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import quickfix.*;
import quickfix.field.EncryptMethod;
import quickfix.field.ResetSeqNumFlag;
import quickfix.fix44.Logon;

*/
/**
 * @author Wayne Sidney
 * Created on {08/05/2022}
 *//*

@Slf4j
public class FIXSession {

    SocketInitiator socketInitiator = null;
    private static FIXSession fixSession = null;

    public static FIXSession getFixSession() {
        if (fixSession == null) {
            fixSession = new FIXSession();
        }
        return fixSession;
    }

    public FIXSession() {
        try {
            SessionSettings sessionSettings = new SessionSettings("initiator.cfg");
            Application application = (Application) new Initiator();
            FileStoreFactory fileStoreFactory = new FileStoreFactory(sessionSettings);
            FileLogFactory fileLogFactory = new FileLogFactory(sessionSettings);
            MessageFactory messageFactory = new DefaultMessageFactory();
            socketInitiator = new SocketInitiator(application,fileStoreFactory,sessionSettings,fileLogFactory,
                    messageFactory);
            socketInitiator.start();
        } catch (ConfigError e) {
            throw new RuntimeException(e);
        }
    }

    public void login(){
        SessionID sessionId = socketInitiator.getSessions().get(0);
        Session.lookupSession(sessionId);
        Logon logon = new Logon();
        logon.set(new ResetSeqNumFlag(true));
        logon.set(new EncryptMethod(0));

        try {
            Session.sendToTarget(logon, sessionId);
        } catch (SessionNotFound e) {
            throw new RuntimeException(e);
        }
    }
}
*/
