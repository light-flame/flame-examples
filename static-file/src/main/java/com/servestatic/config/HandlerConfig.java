package com.servestatic.config;

import com.servestatic.handler.StaticHandler;

import io.lightflame.functions.ConfigFunction;
import io.lightflame.store.FlameHttpStore;

/**
 * HandlerConfig
 */
public class HandlerConfig {

    public ConfigFunction setDefautHandlers() {
        return (config) -> {
            StaticHandler handler = new StaticHandler();

            // flame store
            FlameHttpStore fs =  new FlameHttpStore("");

            fs.httpGET("/*", handler.simpleStatic());
            fs.httpGET("/index", handler.loadingResourceStaticFunc());

            return null;
        };
    }
}