package com.ws;

import io.lightflame.bootstrap.FlameCore;
import io.lightflame.bootstrap.FlameLog4jConfig;
import io.lightflame.http.BasicHttpWsListener;

public class App {
    public static void main(String[] args) {
        new FlameCore()
                .addConfiguration(new Config().setDefautHandlers())
                .addConfiguration(new FlameLog4jConfig().basicConfig())
                .addListener(new BasicHttpWsListener(8080)) // 1
                .addListener(new BasicHttpWsListener(8081)) // 2
                .start(App.class);
        }
}
