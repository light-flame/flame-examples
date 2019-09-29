package demo;

import io.lightflame.bootstrap.ConfigFunction;
import io.lightflame.bootstrap.LightFlame;
import io.lightflame.http.FlameHttpStore;
import io.lightflame.websocket.FlameWsStore;

public class StaticConfig {

    private LightFlame flame;

    StaticConfig(LightFlame fl){
        this.flame = fl;
    }

    public ConfigFunction setDefautHandlers() {
        return () -> {

            // http
            StaticHandler sHandler = new StaticHandler();
            FlameHttpStore httpStore = new FlameHttpStore(8080); // 3
            httpStore.R().httpGET("/", sHandler.getRootFile().and(sHandler.proccess())); // 4
            httpStore.R().httpGET("/static/*", sHandler.getOtherFiles().and(sHandler.proccess())); // 5

            CommandHandler cHandler = new CommandHandler();
            FlameWsStore wsStore = new FlameWsStore(8080);
            wsStore.R().path("/ws", cHandler.inCommand(flame));

        };
    }

}
