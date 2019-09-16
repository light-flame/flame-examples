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

            fs.R().httpGET("/*", handler.simpleStatic());
            fs.R().httpGET("/vuejs", handler.loadingVueJsApp());
            fs.R().httpGET("/static/*", handler.loadingVueJsApp());
            fs.R().httpGET("/index", handler.loadingResourceStaticFunc());

            return null;
        };
    }
}