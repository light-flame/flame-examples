package routing;

import io.lightflame.http.FlameHttpFunction;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

public class Handler {

    public FlameHttpFunction simpleGreeting() {
        return (ctx) -> {
            String name = ctx.getRequest().content().toString(CharsetUtil.UTF_8);
            String greeting = String.format("hello %s", name);
            return ctx.setResponse(new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK, 
                Unpooled.copiedBuffer(greeting, CharsetUtil.UTF_8)
            ));
        };
    }
}