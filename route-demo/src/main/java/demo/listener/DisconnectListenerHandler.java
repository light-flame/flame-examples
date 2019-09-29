package demo.listener;

import io.lightflame.bootstrap.Flame;
import io.lightflame.websocket.FlameWsContext;
import io.lightflame.websocket.FlameWsResponse;

public class DisconnectListenerHandler {

    Flame<FlameWsContext, FlameWsResponse> getRootFile() { // 4
        return (ctx) -> {
            String request = ctx.message();
            return new FlameWsResponse("opa");
        };
    }
}
