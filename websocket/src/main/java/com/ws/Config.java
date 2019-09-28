package com.ws;

import io.lightflame.bootstrap.ConfigFunction;
import io.lightflame.http.FlameHttpStore;
import io.lightflame.websocket.FlameWsStore;

/**
 * Config
 */
public class Config {

    public ConfigFunction setDefautHandlers() {
        return (config) -> {
            WsHandler handler = new WsHandler();

            // http
            FlameHttpStore httpStore = new FlameHttpStore(8080);
            httpStore.R().httpGET("/", handler.getRootFile().and(handler.proccess()));
            httpStore.R().httpGET("/static/*", handler.getOtherFiles().and(handler.proccess()));

            // websocket
            FlameWsStore wsStore =  new FlameWsStore(8081);
            wsStore.R().path("/ws", handler.webSocketFunc());

            return null;
        };
    }
    
}