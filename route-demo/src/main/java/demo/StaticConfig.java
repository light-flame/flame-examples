package demo;

import io.lightflame.bootstrap.ConfigFunction;
import io.lightflame.http.FlameHttpStore;
import io.lightflame.websocket.FlameWsStore;

public class StaticConfig {

    public ConfigFunction setDefautHandlers() {
        return () -> {

            // http
            StaticHandler sHandler = new StaticHandler();
            FlameHttpStore httpStore = new FlameHttpStore(8080); // 3
            httpStore.R().httpGET("/", sHandler.getRootFile().and(sHandler.proccess())); // 4
            httpStore.R().httpGET("/*", sHandler.getOtherFiles().and(sHandler.proccess())); // 5

            FlameWsStore wsStore = new FlameWsStore(8081);
            wsStore.R().path("/ws", null);

        };
    }

}
