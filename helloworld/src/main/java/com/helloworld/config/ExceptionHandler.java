package com.helloworld.config;

import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.*;

import io.lightflame.http.ExceptionHttpFunction;
/**
 * ExceptionHandler
 */
public class ExceptionHandler {

    ExceptionHttpFunction nullPointer() {
        return (e) -> {
            FullHttpResponse response = new DefaultFullHttpResponse(
                HTTP_1_1,BAD_GATEWAY);
            return response;
        };
    }
}