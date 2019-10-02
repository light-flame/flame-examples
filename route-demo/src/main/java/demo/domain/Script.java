package demo.domain;

import java.util.Deque;

public interface Script {
    Deque<ScriptTask> get();
}
