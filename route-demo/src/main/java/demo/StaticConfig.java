package demo;

import io.lightflame.bootstrap.ConfigFunction;
import io.lightflame.bootstrap.LightFlame;
import io.lightflame.http.FlameHttp;
import io.lightflame.websocket.FlameWs;

public class StaticConfig {

    private LightFlame flame;

    StaticConfig(LightFlame fl){
        this.flame = fl;
    }

    public ConfigFunction setDefautHandlers() {
        return () -> {

            // http
            StaticHandler sHandler = new StaticHandler();
            FlameHttp httpStore = new FlameHttp(8080); // 3
            httpStore.R().httpGET("/", sHandler.getRootFile().and(sHandler.proccess())); // 4
            httpStore.R().httpGET("/static/*", sHandler.getOtherFiles().and(sHandler.proccess())); // 5

            WebSocketHandler wsHandler = new WebSocketHandler(flame);
            FlameWs wsStore = new FlameWs(8080);
            wsStore.R().path("/ws", wsHandler.entrypoint());

        };
    }

}
