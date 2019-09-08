package com.helloworld;


import static org.junit.Assert.assertEquals;

import com.helloworld.handler.HandlerConfig;

import org.junit.Before;
import org.junit.Test;

import io.lightflame.bootstrap.LightFlame;
import io.lightflame.http2.FlameHttpServerHandler;
import io.lightflame.http2.FullHttpServerHandler;
import io.lightflame.http2.PipelineFactory;
import io.lightflame.http2.WebSocketFrameHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.util.CharsetUtil;
import javassist.bytecode.Opcode;

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
                HttpMethod.GET, 
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