package com.helloworld.config;

import com.helloworld.handler.HelloWorldHandler;

import io.lightflame.functions.ConfigFunction;
import io.lightflame.store.FlameExceptionStore;
import io.lightflame.store.FlameHttpStore;

/**
 * DefaultConfig
 */
public class HandlerConfig {

    public ConfigFunction setDefautHandlers() {
        return (config) -> {
            HelloWorldHandler helloHandler = new HelloWorldHandler();

            // flame store
            FlameHttpStore fs =  new FlameHttpStore("/api");

            fs.httpGET("/hello/world/simple", helloHandler.simpleGreeting());
            fs.httpGET("/hello/{what}", helloHandler.greetingWithPathParam());
            fs.httpGET("/hello/greeting/with/param", helloHandler.greetingWithQueryUrl());
            fs.httpGET("/hello/with/header", helloHandler.greetingWithHeader());
            fs.httpGET("/hello/*", helloHandler.greetingWithQueryUrl());

            // exception store
            new FlameExceptionStore()
                .add(new NullPointerException(), new ExceptionHandler().nullPointer());

            return null;
        };
    }

}