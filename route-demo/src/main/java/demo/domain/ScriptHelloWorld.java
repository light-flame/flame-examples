package demo.domain;

import java.util.Deque;
import java.util.LinkedList;

public class ScriptHelloWorld implements Script{

    Deque<ScriptTask> task = new LinkedList<>();

    ScriptHelloWorld(){
        task.add(new ScriptTask(new CommandOpenPort(8090, "http"),"You have to open port 8090/http"));
        task.add(new ScriptTask(new CommandClosePort(8090),"You have to close the port 8090"));
    }

    @Override
    public Deque<ScriptTask> get() {
        return task;
    }
}
