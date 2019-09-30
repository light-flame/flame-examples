package demo;

import java.util.*;

public class HelloWorldScript {
    Queue<List<String>> q = new LinkedList<>();

    class Step{
        private String message;
        private boolean readLine;
    }

    HelloWorldScript(){
        q.add(Arrays.asList(
                "use command 'open port' to open a http port"
        ));
        q.add(Arrays.asList(
                "connect the function hello and world together",
                "use the command  f(helloworld)->compose compose.${hello -> world} you will receive a UUID number",
                "save this number, is your function ID"
        ));
        q.add(Arrays.asList(
                "create a handler, use the command 'http post /api/hello' to create a new handler. You will receive a UUID number",
                "use the command 'compose ${hello -> world}' you will receive a UUID number",
                "save this number, is your function ID"
        ));

    }
}