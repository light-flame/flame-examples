package com.helloworld;

import com.helloworld.handler.HandlerConfig;

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
            .start(App.class);
    }
}
