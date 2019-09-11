package com.servestatic;

import com.servestatic.config.HandlerConfig;

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
