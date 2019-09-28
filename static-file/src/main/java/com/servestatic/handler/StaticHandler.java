package com.servestatic.handler;

import java.io.File;
import java.io.FileInputStream;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

/**WebSocketFrameHandler
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
            File file = new File(getClass().getClassLoader().getResource("index.html").getFile());
            FileInputStream inFile = new FileInputStream(file);
            ByteBuf b = Unpooled.copiedBuffer(inFile.readAllBytes());
            inFile.close();
            return ctx.setResponse(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, b));
        };
    }

    public FlameHttpFunction loadingVueJsApp() {
        return (ctx) -> {
            String url = ctx.getPathWithoutPrefix();
            File file = new File("");
            if (url == ""){
                file = new File(getClass().getClassLoader().getResource("dist/index.html").getFile());
            }else{
                file = new File(getClass().getClassLoader().getResource("dist" + url).getFile());
            }
            FileInputStream inFile = new FileInputStream(file);
            ByteBuf b = Unpooled.copiedBuffer(inFile.readAllBytes());
            inFile.close();
            return ctx.setResponse(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, b));
        };
    }


}