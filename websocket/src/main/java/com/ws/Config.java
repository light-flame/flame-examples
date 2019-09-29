package com.ws;

import io.lightflame.bootstrap.ConfigFunction;
import io.lightflame.http.FlameHttp;
import io.lightflame.websocket.FlameWs;

public class Config {

    public ConfigFunction setDefautHandlers() {
        return () -> {

            // http
            StaticHandler sHandler = new StaticHandler();
            FlameHttp httpStore = new FlameHttp(8080); // 3
            httpStore.R().httpGET("/", sHandler.getRootFile().and(sHandler.proccess())); // 4
            httpStore.R().httpGET("/static/*", sHandler.getOtherFiles().and(sHandler.proccess())); // 5

            // websocket
            WsHandler wsHandler = new WsHandler();
            FlameWs wsStore =  new FlameWs(8081); // 3
            wsStore.R().path("/ws", wsHandler.webSocketFunc()); // 6

        };
    }
    
}