package demo.domain;


public interface Command {
    boolean execute();
    String message();
    String toString();
}
