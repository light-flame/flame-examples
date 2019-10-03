package demo.domain;

import io.lightflame.bootstrap.Flame;
import io.lightflame.bootstrap.LightFlame;
import io.lightflame.http.FlameHttp;
import io.netty.handler.codec.http.HttpMethod;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandConnectAll implements Command{
    private FunctionList functionList = new FunctionList();

    private String request;
    private LightFlame lightFlame;
    private FlameHttp flameHttp;
    private FlameHttp.BuildRoute buildRoute;
    private Flame<String, String> function = functionList.base();

    private Pattern FUNCTION_PATTERN = Pattern.compile("function\\{(.*?)}");
    private Pattern METHOD_PATTERN = Pattern.compile("method\\{(.*?)}");
    private Pattern URI_PATTERN = Pattern.compile("uri\\{(.*?)}");
    private Pattern PORT_PATTERN = Pattern.compile("([0-9]{4})");

    public CommandConnectAll(String request, LightFlame lf) {
        lightFlame = lf;
        this.request = request;
    }

    private void transform(String request){
        addFunction(request);
        getPort(request);
        extractMethod(request);
        extractURI(request);
    }

    private void addFunction(String request){
        Matcher m = FUNCTION_PATTERN.matcher(request);
        if (m.find()){
            String[] functionsMap =  m.group().split("\\{")[1].split("}")[0].split("\\+");
            for (String key : functionsMap){
                function = function.and(functionList.getFunction(key));
            }
        }
    }

    private void getPort(String request){
        Matcher m = PORT_PATTERN.matcher(request);
        if (m.find()){
            String port =  m.group();
            flameHttp = new FlameHttp(Integer.parseInt(port));
            buildRoute = flameHttp.R();
        }
    }

    private void extractMethod(String request){
        Matcher m = METHOD_PATTERN.matcher(request);
        if (m.find()){
            String method =  m.group().split("\\{")[1].split("}")[0];
            switch (method){
                case "post":
                    buildRoute.methodRule(HttpMethod.POST);
                    break;
                case "get":
                    buildRoute.methodRule(HttpMethod.GET);
                    break;
            }
        }
    }

    private void extractURI(String request){
        Matcher m = URI_PATTERN.matcher(request);
        if (m.find()){
            String uri =  m.group().split("\\{")[1].split("}")[0];
            buildRoute.httpALL(uri, functionList.mainFunction(function));
        }
    }

    @Override
    public boolean execute() {
        transform(request);
        return true;
    }

    @Override
    public String message() {
        return "function connected";
    }
}
