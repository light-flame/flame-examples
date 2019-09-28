package com.nsqconsumer;


import io.lightflame.bootstrap.LightFlame;
import io.lightflame.nsqconsumer.BasicNsqConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        new LightFlame()
                .addBasicLog4jConfig()
                .addNsqConsumer(new BasicNsqConfig("localhost", 4050, "topic", "ch", null))
                .start(App.class);
    }
}
