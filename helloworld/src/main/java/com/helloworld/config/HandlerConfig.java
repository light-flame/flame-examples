package com.helloworld.config;

import com.helloworld.handler.HelloWorldHandler;

import io.lightflame.functions.ConfigFunction;
import io.lightflame.http.FlameHttpExceptionStore;
import io.lightflame.http.FlameHttpStore;


/**
 * DefaultConfig
 */
public class HandlerConfig {

    public ConfigFunction setDefautHandlers() {
        return (config) -> {
            HelloWorldHandler helloHandler = new HelloWorldHandler();

            // flame store
            FlameHttpStore fs =  new FlameHttpStore("/api");

            fs.R().httpGET("/hello/world/simple", helloHandler.simpleGreeting());
            fs.R().httpGET("/hello/{what}", helloHandler.greetingWithPathParam());
            fs.R().httpGET("/hello/greeting/with/param", helloHandler.greetingWithQueryUrl());
            fs.R().httpGET("/hello/with/header", helloHandler.greetingWithHeader());
            fs.R().httpGET("/hello/*", helloHandler.greetingWithQueryUrl());

            // exception store
            new FlameHttpExceptionStore()
                .add(new NullPointerException(), new ExceptionHandler().nullPointer());

            return null;
        };
    }

}