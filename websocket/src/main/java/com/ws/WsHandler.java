package com.ws;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;

import io.lightflame.bootstrap.Flame;
import io.lightflame.http.FlameHttpContext;
import io.lightflame.http.FlameHttpResponse;
import io.lightflame.websocket.FlameWsContext;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * WsHandler
 */
public class WsHandler {

    private int i = 0;

    private List<String> messages = Arrays.asList( "Hi there, I\"m Fabio and you?", "Nice to meet you", "How are you?", "Not too bad, thanks", "What do you do?", "That\"s awesome", "Codepen is a nice place to stay", "I think you\'re a nice person", "Why do you think that?", "Can you explain?", "Anyway I\'ve gotta go now", "It was a pleasure chat with you", "Time to make a new codepen", "Bye", ":)");


    public Flame<FlameHttpContext, File> getRootFile() {
        return (ctx) -> {
            return new File(getClass().getClassLoader().getResource("dist/index.html").getFile());
        };
    }

    public Flame<FlameHttpContext, File> getOtherFiles() {
        return (ctx) -> {
            String url = ctx.getPathWithoutPrefix();
            return new File(getClass().getClassLoader().getResource("dist/" + url).getFile());
        };
    }

    public Flame<File, FlameHttpResponse> proccess() {
        return (ctx) -> {
            FileInputStream inFile = new FileInputStream(ctx);
            ByteBuf buffer = Unpooled.copiedBuffer(inFile.readAllBytes());
            inFile.close();
            return new FlameHttpResponse(HttpResponseStatus.OK, buffer);
        };
    }

    public Flame<FlameWsContext, FlameWsContext> webSocketFunc() {
        return (ctx) -> {
            ctx.setResponse(this.messages.get(i));
            i++;
            return ctx;
        };
    }
}