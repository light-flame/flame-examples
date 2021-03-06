package com.servestatic.config;

import com.servestatic.handler.StaticHandler;

import io.lightflame.bootstrap.ConfigFunction;
import io.lightflame.http.FlameHttp;

/**
 * HandlerConfig
 */
public class HandlerConfig {

    public ConfigFunction setDefautHandlers() {
        return () -> {
            StaticHandler handler = new StaticHandler();

            // flame store
            FlameHttp fs =  new FlameHttp("");

            fs.R().httpGET("/*", handler.simpleStatic());
            fs.R().httpGET("/vuejs", handler.loadingVueJsApp());
            fs.R().httpGET("/static/*", handler.loadingVueJsApp());
            fs.R().httpGET("/index", handler.loadingResourceStaticFunc());
        };
    }
}