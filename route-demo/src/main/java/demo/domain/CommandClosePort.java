package demo.domain;

import io.lightflame.bootstrap.LightFlame;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandClosePort implements Command{
    private int port;
    private LightFlame lightFlame;
    Pattern PORT_PATTERN = Pattern.compile("([0-9]{4})");

    CommandClosePort(int port){
        this.port = port;
    }

    public CommandClosePort(String request, LightFlame lf) {
        lightFlame = lf;
        Matcher m = PORT_PATTERN.matcher(request);
        if (m.find()){
            this.port = Integer.parseInt(m.group());
        }
    }

    @Override
    public boolean execute() {
        return lightFlame.closeChannel(port);
    }

    @Override
    public String message() {
        return String.format("port %d closed successfully", port);
    }

    @Override
    public String toString() {
        return String.format("close port %d", port);
    }
}
