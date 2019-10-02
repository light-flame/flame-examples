package demo.domain;

public class ScriptTask {
    private Command command;
    private String taskMessage;

    public ScriptTask(Command command, String taskMessage) {
        this.command = command;
        this.taskMessage = taskMessage;
    }

    public String getTaskMessage() {
        return taskMessage;
    }

    public Command getCommand() {
        return command;
    }
}
