package flamehandler;

import io.lightflame.bootstrap.Flame;
import io.lightflame.http.FlameHttpContext;

public class Handler {

    public Flame<FlameHttpContext, Greeting> inHttp() {
        return (ctx) -> {
            Greeting g = new Greeting("Hello", "Daniel");
            return g;
        };
    }

    public Flame<Greeting, Greeting> genericHandler() {
        return (ctx) -> {
            String name = String.valueOf(ctx.toString());
            return ctx;
        };
    }

    public Flame<Greeting, FlameHttpContext> outHttp() {
        return (greeting) -> {
            return null;
        };
    }
}