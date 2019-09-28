package com.ws;

import io.lightflame.bootstrap.LightFlame;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        new LightFlame()
                .addConfiguration(new Config().setDefautHandlers(), null)
                .addBasicLog4jConfig()
                .addHttpAndWsListener(8080)
                .addHttpAndWsListener(8081)
                .start(App.class);
        }
}
