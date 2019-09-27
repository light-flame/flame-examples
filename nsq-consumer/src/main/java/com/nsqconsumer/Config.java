package com.nsqconsumer;

import io.lightflame.bootstrap.ConfigFunction;
import io.lightflame.http.FlameHttpStore;
import io.lightflame.websocket.FlameWsStore;

public class Config {
    public ConfigFunction setDefautHandlers() {
        return (config) -> {
            WsHandler handler = new WsHandler();


            FlameHttpStore httpStore = new FlameHttpStore();
            httpStore.R().httpGET("/*", handler.loadingResourceStaticFunc());

            // flame store
            FlameWsStore wsStore =  new FlameWsStore();
            wsStore.R().path("/ws", handler.webSocketFunc());

            return null;
        };
    }
}
