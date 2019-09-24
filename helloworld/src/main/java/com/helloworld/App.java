package com.helloworld;


import com.helloworld.config.HandlerConfig;

import io.lightflame.bootstrap.LightFlame;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        new LightFlame()
            .addConfiguration(new HandlerConfig().setDefautHandlers(), null)
            .addBasicLog4jConfig()
            .addHttpAndWsListener(8080)
            .start(App.class);
    }
}
