package com.helloworld.config;

import com.helloworld.handler.GreetingHandler;

import io.lightflame.bootstrap.ConfigFunction;
import io.lightflame.http.FlameHttpExceptionStore;
import io.lightflame.http.FlameHttpStore;


/**
 * DefaultConfig
 */
public class HandlerConfig {

    public ConfigFunction setDefautHandlers() {
        return () -> {
            GreetingHandler handler = new GreetingHandler();

            // flame store
            FlameHttpStore fs =  new FlameHttpStore("/api");

            fs.R().httpGET("/greeting/world/simple", handler.simpleGreeting().and(handler.out()));
            fs.R().httpGET("/greeting/{what}", handler.greetingWithPathParam().and(handler.out()));
            fs.R().httpGET("/greeting/with/query/param", handler.greetingWithQueryUrl().and(handler.out()));
            fs.R().httpGET("/greeting/with/header", handler.greetingWithHeader().and(handler.out()));
            fs.R().httpGET("/greeting/*", handler.greetingWithQueryUrl().and(handler.out()));

            // exception store
            new FlameHttpExceptionStore()
                .add(new NullPointerException(), new ExceptionHandler().nullPointer());

        };
    }

}