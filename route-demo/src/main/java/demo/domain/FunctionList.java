package demo.domain;

import io.lightflame.bootstrap.Flame;
import io.lightflame.http.FlameHttpContext;
import io.lightflame.http.FlameHttpResponse;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FunctionList {

    Map<String, Flame<String, String>> functionMap = new HashMap<>();

    FunctionList(){
        functionMap.put("hello", sayHello());
        functionMap.put("world", sayWorld());
    }

    public Flame<String,String> getFunction(String key){
        return functionMap.get(key);
    }

    Flame<String, String> base() {
        return (req) -> "";
    }

    private Flame<String, String> sayWorld() {
        return (req) -> String.format("%s %s", req, "world");
    }

    private Flame<String, String> sayHello() {
        return (req) -> String.format("%s %s", req, "hello");
    }


    public Flame<FlameHttpContext, FlameHttpResponse> mainFunction(Flame<String, String> f1) {
        return (ctx) -> {
            return new FlameHttpResponse(HttpResponseStatus.OK, Unpooled.copiedBuffer(f1.apply("").getBytes()));
        };
    }

}
