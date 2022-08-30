package com.QuickFIxJApplication.FIXApplication;

import com.QuickFIxJApplication.FIXApplication.Acceptor.Acceptor;
import com.QuickFIxJApplication.FIXApplication.Initiator.Initiator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Wayne Sidney
 * Created on {17/04/2022}
 */
@SpringBootApplication
public class FIXApplication {

    public static void main(String[] args) {
        SpringApplication.run(FIXApplication.class, args);

        Acceptor acceptor = new Acceptor();
        Initiator initiator = new Initiator();

        initiator.start();
        acceptor.start();
    }

}
