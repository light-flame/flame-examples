package demo;

import io.lightflame.bootstrap.Flame;
import io.lightflame.bootstrap.LightFlame;
import io.lightflame.websocket.FlameWsContext;
import io.lightflame.websocket.FlameWsResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandHandler {

    interface Command{
        String execute(LightFlame lf, String msg);
    }

    class NotFoundCommand implements Command {

        @Override
        public String execute(LightFlame lf, String msg) {
            return "command not found";
        }
    }

    class OpenPortCommand implements Command {

        Pattern PORT_AND_KIND = Pattern.compile("([A-Za-z]{3,4}[\\/]?[0-9]{4})");

        @Override
        public String execute(LightFlame lf, String msg) {
            Matcher m = PORT_AND_KIND.matcher(msg);
            if (m.find()){
                String fMsg =  m.group().split("/")[0];
                Integer port = Integer.parseInt(fMsg);
//                lf.runListener(port);
            }
            return "port already oppened";
        }
    }

    class ClosePortCommand implements Command{

        Pattern PORT_PATTERN = Pattern.compile("([0-9]{4})");


        @Override
        public String execute(LightFlame lf, String msg) {
            Matcher m = PORT_PATTERN.matcher(msg);
            if (m.find()){
                String fMsg =  m.group();
                Integer port = Integer.parseInt(fMsg);
                lf.closeChannel(port);
                return "Port close successfully";
            }
            return "port already closed";
        }
    }

    enum CommandKind{
        OPEN_PORT("open port"),CLOSE_PORT("close port"), NOT_FOUND("not found");
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
            String msg = getCommandKind()
                    .and(getCommand())
                    .apply(ctx.message())
                    .execute(lf, ctx.message());
            return new FlameWsResponse(msg);
        };
    }

    Flame<String, CommandKind> getCommandKind() {
        return (msg) -> {
            for (CommandKind kind : CommandKind.values()){
                if (msg.startsWith(kind.getValue())){
                    return kind;
                }
            }
            return CommandKind.NOT_FOUND;
        };
    }

    Flame<CommandKind, Command> getCommand() {
        return (commandKind) -> {
            switch (commandKind){
                case OPEN_PORT:
                    return new OpenPortCommand();
                case CLOSE_PORT:
                    return new ClosePortCommand();
                case NOT_FOUND:
                    return new NotFoundCommand();
                default:
                    throw new RuntimeException("error getting kind");
            }
        };
    }

}
