package flamehandler;

import io.lightflame.bootstrap.FlameCore;
import io.lightflame.bootstrap.FlameLog4jConfig;
import io.lightflame.http.BasicHttpWsListener;

public class App {
    public static void main( String[] args ) {
        new FlameCore()
                .addConfiguration(new FlameLog4jConfig().basicConfig())
                .addListener(new BasicHttpWsListener(8080))
                .addConfiguration(new HandlerConfig().setDefautHandlers())
                .start(init.App.class);
    }
}
