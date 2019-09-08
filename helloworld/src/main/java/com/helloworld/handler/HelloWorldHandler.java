package com.helloworld.handler;

import java.util.function.Function;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.CharsetUtil;

import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.*;

/**
 * HelloWorldHandler
 */
public class HelloWorldHandler {


    Function<FullHttpRequest,FullHttpResponse> simpleSayHello() {
        return (request) -> {
            String name = request.content().toString(CharsetUtil.UTF_8);
            String greeting = String.format("hello %s", name);
            return new DefaultFullHttpResponse(
                HTTP_1_1,OK, Unpooled.copiedBuffer(greeting, CharsetUtil.UTF_8));
        };
    }
}