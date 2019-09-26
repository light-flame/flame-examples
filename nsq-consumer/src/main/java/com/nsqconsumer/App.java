package com.nsqconsumer;


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
            .addBasicLog4jConfig()
            .addNsqConsumer("localhost", 4150)
            .start(App.class);
    }
}
