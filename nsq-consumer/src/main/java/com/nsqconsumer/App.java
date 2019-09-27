package com.nsqconsumer;


import io.lightflame.bootstrap.LightFlame;
import io.lightflame.nsqconsumer.BasicNsqConfig;

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
            .addNsqConsumer(new BasicNsqConfig("localhost",9999,"topic1","test"))
            .start(App.class);
    }
}
