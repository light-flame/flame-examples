package com.helloworld;


import static org.junit.Assert.assertEquals;

import com.helloworld.config.HandlerConfig;

import org.junit.Before;
import org.junit.Test;

import io.lightflame.bootstrap.LightFlame;
import io.lightflame.http2.FlameHttpServerHandler;
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
            new FlameHttpServerHandler()
        );
    }

    @Before
    public void configureLightFlame(){
        new LightFlame()
            .runConfiguration(new HandlerConfig().setDefautHandlers(), null);
    }

    @Test
    public void simpleHelloWorld(){
        FullHttpRequest httpRequest = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_1, 
                HttpMethod.POST, 
                "/api/hello/simple",
                Unpooled.copiedBuffer("Daniel", CharsetUtil.UTF_8)
        );
        // httpRequest.headers().add("name", "Daniel");
        channel.writeInbound(httpRequest);

        // get response
        FullHttpResponse resp = channel.readOutbound();
        String msg = resp.content().toString(CharsetUtil.UTF_8);
        assertEquals(msg.equals("hello Daniel"), true);

    }
}