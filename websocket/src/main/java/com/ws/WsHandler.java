package com.ws;

import java.util.Arrays;
import java.util.List;

import io.lightflame.bootstrap.Flame;
import io.lightflame.websocket.FlameWsContext;
import io.lightflame.websocket.FlameWsResponse;


public class WsHandler {

    private int i = -1;

    private List<String> messages = Arrays.asList( "Hi there, I\"m Fabio and you?", "Nice to meet you", "How are you?", "Not too bad, thanks", "What do you do?", "That\"s awesome", "Codepen is a nice place to stay", "I think you\'re a nice person", "Why do you think that?", "Can you explain?", "Anyway I\'ve gotta go now", "It was a pleasure chat with you", "Time to make a new codepen", "Bye", ":)");

    Flame<FlameWsContext, FlameWsResponse> webSocketFunc() { // 6
        return (ctx) -> {
            i++;
            return new FlameWsResponse(this.messages.get(i));
        };
    }
}