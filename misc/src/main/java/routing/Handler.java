package routing;

import io.lightflame.bootstrap.Flame;
import io.lightflame.http.FlameHttpContext;
import io.lightflame.http.FlameHttpResponse;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;

public class Handler {

    public Flame<FlameHttpContext, FlameHttpResponse> simpleGreeting() {
        return (ctx) -> {
            String name = ctx.getRequest().content().toString(CharsetUtil.UTF_8);
            String greeting = String.format("hello %s", name);
            return new FlameHttpResponse(HttpResponseStatus.OK, Unpooled.copiedBuffer(greeting, CharsetUtil.UTF_8));
        };
    }
}