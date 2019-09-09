package com.helloworld.config;

import com.helloworld.handler.HelloWorldHandler;

import io.lightflame.store.FlameExceptionStore;
import io.lightflame.store.FlameHttpStore;
import io.lightflame.functions.ConfigFunction;

/**
 * DefaultConfig
 */
public class HandlerConfig {

    public ConfigFunction setDefautHandlers() {
        return (config) -> {
            HelloWorldHandler helloHandler = new HelloWorldHandler();

            // flame store
            FlameHttpStore fs =  new FlameHttpStore("/api");

            fs.httpGET("/hello/simple", helloHandler.simpleGreeting());
            fs.httpGET("/hello/greeting/{name}", helloHandler.greetingWithPathParam());

            // exception store
            new FlameExceptionStore()
                .add(new NullPointerException(), new ExceptionHandler().nullPointer());

            return null;
        };
    }

}