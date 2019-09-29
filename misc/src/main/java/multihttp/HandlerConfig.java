package multihttp;

import io.lightflame.bootstrap.ConfigFunction;
import io.lightflame.http.FlameHttp;
import routing.Handler;

public class HandlerConfig {

    public ConfigFunction setDefautHandlers() {
        return () -> {
            Handler handler = new Handler();

            // flame store to port 8080
            FlameHttp fs1 = new FlameHttp(8080,"/api");

            fs1.R().httpGET("/*", handler.simpleGreeting()); // widecard route
            fs1.R().httpGET("/path/to/my/url", handler.simpleGreeting());

            // flame store to port 8080
            FlameHttp fs2 = new FlameHttp(8090,"/api");

            fs2.R().httpGET("/*", handler.simpleGreeting()); // widecard route
            fs2.R().httpGET("/path/to/my/url", handler.simpleGreeting());
        };
    }
}