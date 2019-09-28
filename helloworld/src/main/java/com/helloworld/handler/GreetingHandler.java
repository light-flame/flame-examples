package com.helloworld.handler;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;

import io.lightflame.bootstrap.Flame;
import io.lightflame.http.FlameHttpContext;
import io.lightflame.http.FlameHttpResponse;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * HelloWorldHandler
 */
public class GreetingHandler {


    public Flame<FlameHttpContext, String> simpleGreeting() {
        return (ctx) -> ctx.getRequest().content().toString(CharsetUtil.UTF_8);
    }

    public Flame<FlameHttpContext, String> greetingWithPathParam() {
        return (ctx) -> ctx.getPathParam("what");
    }

    public Flame<FlameHttpContext, String> greetingWithQueryUrl() {
        return (ctx) -> ctx.getQueryUrl("what");
    }

    public Flame<FlameHttpContext, String> greetingWithHeader() {
        return (ctx) -> ctx.getRequest().headers().get("what");
    }

    public Flame<String, FlameHttpResponse> out() {
        return (name) -> {
            String greeting = String.format("hello %s", name);
            return new FlameHttpResponse(OK, Unpooled.copiedBuffer(greeting, CharsetUtil.UTF_8));
        };
    }
}