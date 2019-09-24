package com.helloworld;


import static org.junit.Assert.assertEquals;

import com.helloworld.config.HandlerConfig;

import org.junit.Before;
import org.junit.Test;

import io.lightflame.bootstrap.LightFlame;
import io.lightflame.http.HttpServerHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * TestingHelloWorld
 */
public class TestingHelloWorld {

    EmbeddedChannel channel;

    @Before
    public void createChannel(){
        channel = new EmbeddedChannel(
            new HttpResponseDecoder(), 
            new HttpServerHandler()
        );
    }

    @Before
    public void configureLightFlame(){
        new LightFlame()
            .addConfiguration(new HandlerConfig().setDefautHandlers(), null)
            .startMock(TestingHelloWorld.class);
    }

    @Test
    public void simpleHelloWorld(){
        FullHttpRequest httpRequest = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_1, 
                HttpMethod.GET, 
                "/api/hello/world/simple",
                Unpooled.copiedBuffer("world", CharsetUtil.UTF_8)
        );
        channel.writeInbound(httpRequest);

        // get response
        FullHttpResponse ctx = channel.readOutbound();
        String msg = ctx.content().toString(CharsetUtil.UTF_8);
        assertEquals(msg.equals("hello world"), true);

    }

    @Test
    public void simpleHelloUrlParam(){
        FullHttpRequest httpRequest = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_1, 
                HttpMethod.GET, 
                "/api/hello/world"
        );
        channel.writeInbound(httpRequest);

        // get response
        FullHttpResponse ctx = channel.readOutbound();
        String msg = ctx.content().toString(CharsetUtil.UTF_8);
        assertEquals(msg.equals("hello world"), true);

    }

    @Test
    public void simpleHelloQueryParam(){
        FullHttpRequest httpRequest = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_1, 
                HttpMethod.GET, 
                "/api/hello/greeting/with/param?what=world"
        );
        channel.writeInbound(httpRequest);

        // get response
        FullHttpResponse ctx = channel.readOutbound();
        String msg = ctx.content().toString(CharsetUtil.UTF_8);
        assertEquals(msg.equals("hello world"), true);
    }

    @Test
    public void wideCardUrl(){
        FullHttpRequest httpRequest = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_1, 
                HttpMethod.GET, 
                "/api/hello/with/generic?what=world"
        );
        channel.writeInbound(httpRequest);

        // get response
        FullHttpResponse ctx = channel.readOutbound();
        String msg = ctx.content().toString(CharsetUtil.UTF_8);
        assertEquals(msg.equals("hello world"), true);
    }

    @Test
    public void headerParam(){
        FullHttpRequest httpRequest = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_1, 
                HttpMethod.GET, 
                "/api/hello/with/header"
        );
        httpRequest.headers().add("what", "world");
        channel.writeInbound(httpRequest);

        // get response
        FullHttpResponse ctx = channel.readOutbound();
        String msg = ctx.content().toString(CharsetUtil.UTF_8);
        assertEquals(msg.equals("hello world"), true);
    }
}