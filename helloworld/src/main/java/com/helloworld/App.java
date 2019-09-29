package com.helloworld;


import com.helloworld.config.HandlerConfig;

import io.lightflame.bootstrap.FlameCore;
import io.lightflame.bootstrap.FlameLog4jConfig;
import io.lightflame.http.BasicHttpWsListener;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        new FlameCore()
                .addConfiguration(new HandlerConfig().setDefautHandlers())
                .addConfiguration(new FlameLog4jConfig().basicConfig())
                .addListener(new BasicHttpWsListener(8080))
                .start(App.class);
    }
}
