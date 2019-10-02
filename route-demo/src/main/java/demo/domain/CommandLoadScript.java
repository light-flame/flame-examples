package demo.domain;

public class CommandLoadScript implements Command{

    private Script script;

    CommandLoadScript(String req){
        if (req.contains("hello")){
            this.script = new ScriptHelloWorld();
        }
    }

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public String message() {
        return "Success loaded script";
    }

    public Script getScript(){
        return script;
    }
}
