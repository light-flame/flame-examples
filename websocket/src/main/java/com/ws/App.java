package com.ws;

import io.lightflame.bootstrap.LightFlame;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        new LightFlame()
            .runConfiguration(new Config().setDefautHandlers(), null)
            .start(App.class);    
        }
}
