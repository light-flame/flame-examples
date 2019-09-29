package demo;

import io.lightflame.bootstrap.Flame;
import io.lightflame.bootstrap.LightFlame;
import io.lightflame.http.BasicHttpWsListener;
import io.lightflame.websocket.FlameWsContext;
import io.lightflame.websocket.FlameWsResponse;

public class CommandHandler {

    enum CommandKind{
        OPEN_PORT("open port"),CLOSE_PORT("close port");
        final String value;
        CommandKind(String s) {
            this.value = s;
        }

        public String getValue() {
            return value;
        }
    }

    public Flame<FlameWsContext, FlameWsResponse> inCommand(LightFlame lf) {
        return (ctx) -> {
            String request = ctx.getRequest();
            Flame<String,String> command = checkCommandKind(lf).apply(request);
            command.apply(request);
            return new FlameWsResponse("opa");
        };
    }

    Flame<String, Flame<String, String>> checkCommandKind(LightFlame lf) {
        return (command) -> {
            for (CommandKind kind : CommandKind.values()){
                if (command.startsWith(kind.getValue())){
                    return getCommandFunction(lf).apply(kind);
                }
            }
            throw new NullPointerException("command not found");
        };
    }

    Flame<CommandKind, Flame<String, String>> getCommandFunction(LightFlame lf) {
        return (commandKind) -> {
            switch (commandKind){
                case OPEN_PORT:
                    return openPortCommand(lf);
                case CLOSE_PORT:
                    return closePortCommand(lf);
                default:
                    throw new NullPointerException("command doesnt exist");
            }
        };
    }

    Flame<String, String> openPortCommand(LightFlame lf) {
        return (command) -> {
            command = command.split(CommandKind.OPEN_PORT.value)[1].trim();
            lf.runListener(new BasicHttpWsListener(8085));
            return null;
        };
    }

    Flame<String, String> closePortCommand(LightFlame lf) {
        return (commandKind) -> {
            return null;
        };
    }

}
