package com.helloworld.handler;

import java.util.function.Function;

import com.helloworld.model.Greeting;

import io.lightflame.context.FlameHttpContext;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.CharsetUtil;

import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.*;

/**
 * HelloWorldHandler
 */
public class HelloWorldHandler {

    public Function<Greeting,FullHttpResponse> inboundGreeting() {
        return (greeting) -> {
            String msg = String.format("hello %s", greeting.getName());
            return new DefaultFullHttpResponse(
                HTTP_1_1,OK, Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
        };
    }


    public Function<FlameHttpContext,FlameHttpContext> simpleGreeting() {
        return (ctx) -> {
            String name = ctx.getRequest().content().toString(CharsetUtil.UTF_8);
            String greeting = String.format("hello %s", name);
            FullHttpResponse res =  new DefaultFullHttpResponse(
                HTTP_1_1,OK, Unpooled.copiedBuffer(greeting, CharsetUtil.UTF_8));
            return ctx.setResponse(res);
        };
    }
}