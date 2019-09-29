package demo.listener;

import io.lightflame.bootstrap.Flame;
import io.lightflame.websocket.FlameWsContext;
import io.lightflame.websocket.FlameWsResponse;

import java.util.Optional;

public class DisconnectListenerHandler {

    Flame<FlameWsContext, FlameWsResponse> getRootFile() { // 4
        return (ctx) -> {
            String request = ctx.getRequest();
            return new FlameWsResponse("opa");
        };
    }
}
