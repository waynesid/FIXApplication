package com.QuickFIxJApplication.FIXApplication.API;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URI;

/**
 * @author Wayne Sidney
 * Created on {28/06/2022}
 */
public class SocketIO {
    URI uri = URI.create( "https://stream.globitex.com:8080");

    //TODO set IO factory options and auth/manager options here
    IO.Options options = IO.Options.builder().build();

    Socket socket = IO.socket(uri, options);
    Socket tradeSocket = IO.socket(URI.create(" https://stream.globitex.com:8080/trades"), options);
    Socket tickerSocket = IO.socket(URI.create(" https://stream.globitex.com:8080/ticker"), options);


    //listening for events
    Emitter.Listener listener = new Emitter.Listener() {
        @Override
        public void call(Object... objects) {
        }
    };




}
