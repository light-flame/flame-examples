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
            .runConfiguration(new HandlerConfig().setDefautHandlers(), null)
            .addBasicLog4jConfig()
            .start(App.class);
    }
}
