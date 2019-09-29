package flamehandler;

import io.lightflame.bootstrap.ConfigFunction;
import io.lightflame.http.FlameHttpStore;

public class HandlerConfig {

    public ConfigFunction setDefautHandlers() {
        return () -> {
            Handler h = new Handler();

            h.inHttp().and(h.outHttp());

            // flame store
            FlameHttpStore  fs  =  new  FlameHttpStore("");
            fs.R().httpGET("/generic/url", null);
        };
    }
}