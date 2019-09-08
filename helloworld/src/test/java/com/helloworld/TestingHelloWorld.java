package com.helloworld;


import com.helloworld.handler.HandlerConfig;

import org.junit.Before;
import org.junit.Test;

import io.lightflame.bootstrap.LightFlame;
import io.lightflame.http2.PipelineFactory;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * TestingHelloWorld
 */
public class TestingHelloWorld {

    EmbeddedChannel channel;

    @Before
    public void createChannel(){
        channel = new EmbeddedChannel(PipelineFactory.createChannels());
    }

    @Before
    public void configureLightFlame(){
        new LightFlame()
            .runConfiguration(new HandlerConfig().setDefautHandlers(), null);
    }

    @Test
    public void testHandler(){
        FullHttpRequest httpRequest = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_1, 
                HttpMethod.GET, 
                "/api/hello",
                Unpooled.copiedBuffer("Daniel", CharsetUtil.UTF_8)
        );
        httpRequest.headers().add("name", "Daniel");
        channel.writeInbound(httpRequest);
        Object resp = channel.readInbound();
        FullHttpResponse httpResponse = channel.readOutbound();
        assert(resp.equals("dada dakdkad dada"));
    }
}