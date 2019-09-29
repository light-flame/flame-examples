package demo;

import io.lightflame.bootstrap.Flame;
import io.lightflame.http.FlameHttpContext;
import io.lightflame.http.FlameHttpResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.io.File;
import java.io.FileInputStream;

public class StaticHandler {

    Flame<FlameHttpContext, File> getRootFile() { // 4
        return (ctx) -> {
            return new File(getClass().getClassLoader().getResource("static/index.html").getFile());
        };
    }

    Flame<FlameHttpContext, File> getOtherFiles() { // 5
        return (ctx) -> {
            String url = ctx.getPathWithoutPrefix();
            return new File(getClass().getClassLoader().getResource("static/" + url).getFile());
        };
    }

    Flame<File, FlameHttpResponse> proccess() { // 4 and 5
        return (ctx) -> {
            FileInputStream inFile = new FileInputStream(ctx);
            ByteBuf buffer = Unpooled.copiedBuffer(inFile.readAllBytes());
            inFile.close();
            return new FlameHttpResponse(HttpResponseStatus.OK, buffer);
        };
    }
}