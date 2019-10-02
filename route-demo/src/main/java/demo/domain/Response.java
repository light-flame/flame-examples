package demo.domain;

public class Response {
    private Command command;
    private Boolean success;
    private ScriptTask task;
    private String message;

   Response(Command cmd){
       this.command = cmd;
   }

    public Command getCommand() {
        return command;
    }

    public void setSuccess(Boolean success) {
       message = command.message();
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setMessage(String msg){
       message = msg;
    }

    public void setTask(ScriptTask task) {
        this.task = task;
    }

    public String getMessage() {
       if (task != null){
           return String.format("%s\n%s", message, task.getTaskMessage());
       }
        return message;
    }
}
