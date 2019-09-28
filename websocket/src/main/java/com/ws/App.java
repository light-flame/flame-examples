package com.ws;

import io.lightflame.bootstrap.LightFlame;
import io.lightflame.http.BasicHttpWsListener;

public class App {
    public static void main(String[] args) {
        new LightFlame()
                .addConfiguration(new Config().setDefautHandlers(), null)
                .addBasicLog4jConfig()
                .addListener(new BasicHttpWsListener(8080)) // 1
                .addListener(new BasicHttpWsListener(8081)) // 2
                .start(App.class);
        }
}
