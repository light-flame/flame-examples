package demo.domain;

import io.lightflame.bootstrap.LightFlame;
import io.lightflame.http.BasicHttpWsListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandOpenPort implements Command{

    private int port;
    private String kind;
    private LightFlame lightFlame;

    Pattern PORT_AND_KIND = Pattern.compile("([0-9]{4}[\\/]?[A-Za-z]{3,4})");

    public CommandOpenPort(String request, LightFlame lf) {
        lightFlame = lf;
        Matcher m = PORT_AND_KIND.matcher(request);
        if (m.find()){
            String[] fMsg =  m.group().split("/");
            this.port = Integer.parseInt(fMsg[0]);
            this.kind = fMsg[1];
        }
    }

    public CommandOpenPort(int port, String kind) {
        this.port = port;
        this.kind = kind;
    }

    @Override
    public boolean execute() {
        if (kind == null){
            return false;
        }
        if (kind.equals("http")){
            lightFlame.runListener(new BasicHttpWsListener(port));
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s%d", this.kind, this.port);
    }

    @Override
    public String message() {
        return String.format("port %d for %s open with success", port, kind);
    }

}
