package com.servestatic.config;

import com.servestatic.handler.StaticHandler;

import io.lightflame.functions.ConfigFunction;
import io.lightflame.http.FlameHttpStore;

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
            fs.httpGET("/vuejs", handler.loadingVueJsApp());
            fs.httpGET("/static/*", handler.loadingVueJsApp());
            fs.httpGET("/index", handler.loadingResourceStaticFunc());

            return null;
        };
    }
}