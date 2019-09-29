package com.nsqconsumer;


import io.lightflame.bootstrap.LightFlame;
import io.lightflame.nsqconsumer.BasicNsqConfig;
import io.lightflame.nsqconsumer.BasicNsqConsumerListener;
import io.lightflame.nsqconsumer.NsqConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        NsqConfig nsqconf = new BasicNsqConfig("localhost", 4050, "topic", "ch", null);
        new LightFlame()
                .addBasicLog4jConfig()
                .addListener(new BasicNsqConsumerListener(nsqconf))
                .start(App.class);
    }
}
