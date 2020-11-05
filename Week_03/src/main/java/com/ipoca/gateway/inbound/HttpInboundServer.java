package com.ipoca.gateway.inbound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpInboundServer {
    private static Logger logger = LoggerFactory.getLogger(HttpInboundServer.class);

    private int port;

    private String proxyServer;

    public HttpInboundServer(int port, String proxyServer){
        this.port = port;
        this.proxyServer = proxyServer;
    }
}
