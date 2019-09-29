package com.nsqconsumer;


import io.lightflame.bootstrap.FlameCore;
import io.lightflame.bootstrap.FlameLog4jConfig;
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
        new FlameCore()
                .addConfiguration(new FlameLog4jConfig().basicConfig())
                .addListener(new BasicNsqConsumerListener(nsqconf))
                .start(App.class);
    }
}
