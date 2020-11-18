package com.ipoca.gateway.inbound;

import com.ipoca.gateway.filter.HttpRequestFilter;
import com.ipoca.gateway.outbound.HttpOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);
    private final String proxyServer;
    private HttpOutboundHandler handler;

    public HttpInboundHandler(String proxyServer){
        this.proxyServer = proxyServer;
        handler = new HttpOutboundHandler(this.proxyServer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            HttpRequestFilter filter = (fullReques,ct)->filterHead(fullReques,ct);
            filter.filter(fullHttpRequest,ctx);
            handler.handler(fullHttpRequest,ctx);
        } catch (Exception e){
            e.printStackTrace();
        } finally {

        }
    }

    private void filterHead(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        fullHttpRequest.headers().set("nio","xubang");
    }
}
