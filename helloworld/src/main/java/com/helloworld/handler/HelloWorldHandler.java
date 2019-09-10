package com.helloworld.handler;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import io.lightflame.functions.FlameHttpFunction;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.util.CharsetUtil;

/**
 * HelloWorldHandler
 */
public class HelloWorldHandler {


    public FlameHttpFunction simpleGreeting() {
        return (ctx) -> {
            String name = ctx.getRequest().content().toString(CharsetUtil.UTF_8);
            String greeting = String.format("hello %s", name);
            return ctx.setResponse(new DefaultFullHttpResponse(
                HTTP_1_1,OK, Unpooled.copiedBuffer(greeting, CharsetUtil.UTF_8)));
        };
    }

    public FlameHttpFunction greetingWithPathParam() {
        return (ctx) -> {
            String name = ctx.getPathParam("what");
            String greeting = String.format("hello %s", name);
            return ctx.setResponse(new DefaultFullHttpResponse(
                HTTP_1_1,OK, Unpooled.copiedBuffer(greeting, CharsetUtil.UTF_8)));
        };
    }

    public FlameHttpFunction greetingWithQueryUrl() {
        return (ctx) -> {
            String name = ctx.getQueryUrl("what");
            String greeting = String.format("hello %s", name);
            return ctx.setResponse(new DefaultFullHttpResponse(
                HTTP_1_1,OK, Unpooled.copiedBuffer(greeting, CharsetUtil.UTF_8)));
        };
    }

    public FlameHttpFunction greetingWithHeader() {
        return (ctx) -> {
            String name = ctx.getHeaders().get("what");
            String greeting = String.format("hello %s", name);
            return ctx.setResponse(new DefaultFullHttpResponse(
                HTTP_1_1,OK, Unpooled.copiedBuffer(greeting, CharsetUtil.UTF_8)));
        };
    }
}