package multihttp;

import io.lightflame.bootstrap.ConfigFunction;
import io.lightflame.http.FlameHttpStore;
import routing.Handler;

public class HandlerConfig {

    public ConfigFunction setDefautHandlers() {
        return (config) -> {
            Handler handler = new Handler();

            // flame store to port 8080
            FlameHttpStore fs1 = new FlameHttpStore(8080,"/api");

            fs1.R().httpGET("/*", handler.simpleGreeting()); // widecard route
            fs1.R().httpGET("/path/to/my/url", handler.simpleGreeting());

            // flame store to port 8080
            FlameHttpStore fs2 = new FlameHttpStore(8090,"/api");

            fs2.R().httpGET("/*", handler.simpleGreeting()); // widecard route
            fs2.R().httpGET("/path/to/my/url", handler.simpleGreeting());

            return null;
        };
    }
}