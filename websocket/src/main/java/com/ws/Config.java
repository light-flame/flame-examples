package com.ws;

import io.lightflame.bootstrap.ConfigFunction;
import io.lightflame.http.FlameHttpStore;
import io.lightflame.websocket.FlameWsStore;

public class Config {

    public ConfigFunction setDefautHandlers() {
        return () -> {

            // http
            StaticHandler sHandler = new StaticHandler();
            FlameHttpStore httpStore = new FlameHttpStore(8080); // 3
            httpStore.R().httpGET("/", sHandler.getRootFile().and(sHandler.proccess())); // 4
            httpStore.R().httpGET("/static/*", sHandler.getOtherFiles().and(sHandler.proccess())); // 5

            // websocket
            WsHandler wsHandler = new WsHandler();
            FlameWsStore wsStore =  new FlameWsStore(8081); // 3
            wsStore.R().path("/ws", wsHandler.webSocketFunc()); // 6

        };
    }
    
}