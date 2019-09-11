package com.servestatic.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import io.lightflame.context.FlameHttpContext;
import io.lightflame.functions.FlameHttpFunction;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**
 * StaticHandler
 */
public class StaticHandler {

    public FlameHttpFunction simpleStatic() {
        return (ctx) -> {
            String staticFile = "<!DOCTYPE html><html><body><h1>Simple Hello World</span></h1></body></html>";
            return ctx.setResponse(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                    Unpooled.copiedBuffer(staticFile, CharsetUtil.UTF_8)));
        };
    }

    public FlameHttpFunction loadingResourceStaticFunc() {
        return (ctx) -> {
            try {
                return loadingResourceStatic(ctx);
            }catch(Exception e){
                throw new RuntimeException(e);
            }
        };
    }

    public FlameHttpContext loadingResourceStatic(FlameHttpContext ctx)throws IOException {
        File file = new File(getClass().getClassLoader().getResource("index.html").getFile());
        FileInputStream inFile = new FileInputStream(file);
        ByteBuf b = Unpooled.copiedBuffer(inFile.readAllBytes());
        inFile.close();
        return ctx.setResponse(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, b));
    }


}