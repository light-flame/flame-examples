package com.helloworld;


import com.helloworld.config.HandlerConfig;

import io.lightflame.bootstrap.LightFlame;
import io.lightflame.http.BasicHttpWsListener;

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
                .addListener(new BasicHttpWsListener(8080))
                .start(App.class);
    }
}
