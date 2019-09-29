package demo;

import io.lightflame.bootstrap.LightFlame;
import io.lightflame.http.BasicHttpWsListener;

public class App {
    public static void main(String[] args) {
        new LightFlame()
                .addBasicLog4jConfig()
                .addConfiguration(new StaticConfig().setDefautHandlers(), null)
                .addListener(new BasicHttpWsListener(8080))
                .start(App.class);
    }
}
