package com.helloworld.handler;

import com.helloworld.handler.HelloWorldHandler;

import io.lightflame.bean.FlameExceptionStore;
import io.lightflame.bean.FlameHttpStore;
import io.lightflame.functions.ConfigFunction;

/**
 * DefaultConfig
 */
public class HandlerConfig {

    public ConfigFunction setDefautHandlers() {
        return (config) -> {
            HelloWorldHandler helloHandler = new HelloWorldHandler();

            // flame store
            new FlameHttpStore("/api")
                .httpGET("/hello", helloHandler.sayHello());

            // exception store
            new FlameExceptionStore()
                .add(new NullPointerException(), new ExceptionHandler().nullPointer());

            return null;
        };
    }

}