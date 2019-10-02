package demo.domain;

import io.lightflame.bootstrap.LightFlame;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Function;

public class Services {

    Util util;

    public Services(LightFlame lf){
        util = new Util(lf);
    }
    // entrance point 1
    public Function<String, String> exec() {
        return (req) -> {
            Command cmd = util.getCommand(req);
            if (cmd == null){
                return "command not found";
            }
            return executeCommand().apply(new Response(cmd)).getMessage();
        };
    }
    Function<Response, Response> executeCommand() {
        return (cmdResponse) -> {
            cmdResponse.setSuccess(cmdResponse.getCommand().execute());
            return executionResult().apply(cmdResponse);
        };
    }

    Function<Response, Response> executionResult() {
        return (cmdResponse) -> {
            if (cmdResponse.getSuccess()){
                hasScriptLoaded().apply(cmdResponse);
                return cmdResponse;
            }
            cmdResponse.setMessage("command with errors or incomplete");
            return cmdResponse;
        };
    }

    Deque<ScriptTask> tasks = new LinkedList<>();

    Function<Response, Response> hasScriptLoaded() {
        return (cmdResponse) -> {
            if (tasks.size() > 0){
                return isCommandAValidTask().apply(cmdResponse);
            }
            return commandIsLoadScript().apply(cmdResponse);
        };
    }

    Function<Response, Response> isCommandAValidTask() {
        return (cmdResponse) -> {
            ScriptTask currentTask =  tasks.poll();
            assert currentTask != null;
            String a = cmdResponse.getCommand().toString();
            String b = currentTask.getCommand().toString();
            if (a.equals(b)){
                cmdResponse.setTask(currentTask);
                return cmdResponse;
            }
            tasks.addFirst(currentTask);
            cmdResponse.setMessage("command isnt the same");
            return cmdResponse;
        };
    }

    Function<Response, Response> commandIsLoadScript() {
        return (cmdResponse) -> {
            if (cmdResponse.getCommand() instanceof CommandLoadScript){
                CommandLoadScript cmd = (CommandLoadScript)cmdResponse.getCommand();
                tasks = cmd.getScript().get();
            }
            return cmdResponse;
        };
    }

}
