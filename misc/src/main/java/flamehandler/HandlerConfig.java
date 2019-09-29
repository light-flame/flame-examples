package flamehandler;

import io.lightflame.bootstrap.ConfigFunction;
import io.lightflame.http.FlameHttp;

public class HandlerConfig {

    public ConfigFunction setDefautHandlers() {
        return () -> {
            Handler h = new Handler();

            h.inHttp().and(h.outHttp());

            // flame store
            FlameHttp fs  =  new FlameHttp("");
            fs.R().httpGET("/generic/url", null);
        };
    }
}