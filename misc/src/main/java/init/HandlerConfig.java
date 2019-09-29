package init;

import io.lightflame.bootstrap.ConfigFunction;
import io.lightflame.http.FlameHttp;

public class HandlerConfig {

    public ConfigFunction setDefautHandlers() {
        return () -> {
            Handler handler = new Handler();

            // flame store
            FlameHttp fs =  new FlameHttp("/api");

            fs.R().httpGET("/hello/world/simple", handler.simpleGreeting());
        };
    }
}