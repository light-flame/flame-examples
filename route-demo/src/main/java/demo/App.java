package demo;

import io.lightflame.bootstrap.FlameCore;
import io.lightflame.bootstrap.FlameLog4jConfig;
import io.lightflame.bootstrap.LightFlame;
import io.lightflame.http.BasicHttpWsListener;

public class App {
    public static void main(String[] args) {
        LightFlame fl =  new FlameCore()
                .addConfiguration(new FlameLog4jConfig().basicConfig())
                .addListener(new BasicHttpWsListener(8080));
        fl.addConfiguration(new StaticConfig(fl).setDefautHandlers()).start(App.class);
    }
}
