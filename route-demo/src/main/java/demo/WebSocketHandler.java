package demo;

import demo.domain.Services;
import io.lightflame.bootstrap.Flame;
import io.lightflame.bootstrap.LightFlame;
import io.lightflame.websocket.FlameWsContext;

public class WebSocketHandler {

    private Services service;

    WebSocketHandler(LightFlame lightFlame){
        service = new Services(lightFlame);
    }

    Flame<FlameWsContext, FlameWsContext> entrypoint() {
        return (ctx) -> {
            String msg = service.exec().apply(ctx.message());
            ctx.writeToChannel(msg);
            return ctx;
        };
    }
}
