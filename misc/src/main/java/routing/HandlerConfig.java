package routing;

import io.lightflame.bootstrap.ConfigFunction;
import io.lightflame.http.FlameHttp;

public class HandlerConfig {

    public ConfigFunction setDefautHandlers() {
        return () -> {
            Handler handler = new Handler();


            // flame store
            FlameHttp fs  =  new FlameHttp("/api");

            fs.R().httpGET("/*", handler.simpleGreeting()); // widecard route
            fs.R().httpGET("/path/to/my/url", handler.simpleGreeting());
            fs.R().httpGET("/hello/{name}", handler.simpleGreeting()); // dynamic route
            fs.R().httpPOST("/this/is/a/post", handler.simpleGreeting());
            // complex filters
            fs.R()
                .headerRule("x-auth","abc")
                .queryRule("name","daniel")
                .pathRule("name","daniel")
                .httpALL("/*", handler.simpleGreeting());
        };
    }
}