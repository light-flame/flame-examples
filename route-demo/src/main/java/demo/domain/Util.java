package demo.domain;

import io.lightflame.bootstrap.LightFlame;

public class Util {

    LightFlame lightFlame;

    Util(LightFlame lf){
        lightFlame = lf;
    }

    Command getCommand(String request){
        if (request.startsWith("load script")){
            return new CommandLoadScript(request);
        }
        if (request.startsWith("open port")){
            return new CommandOpenPort(request, lightFlame);
        }
        if (request.startsWith("close port")){
            return new CommandClosePort(request, lightFlame);
        }
        if (request.startsWith("connect")){
            return new CommandConnectAll(request, lightFlame);
        }
        return null;
    }
}
