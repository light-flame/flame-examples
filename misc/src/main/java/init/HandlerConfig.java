package init;

import io.lightflame.bootstrap.ConfigFunction;
import io.lightflame.http.FlameHttpStore;

public class HandlerConfig {

    public ConfigFunction setDefautHandlers() {
        return () -> {
            Handler handler = new Handler();

            // flame store
            FlameHttpStore fs =  new FlameHttpStore("/api");

            fs.R().httpGET("/hello/world/simple", handler.simpleGreeting());
        };
    }
}