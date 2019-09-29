import demo.CommandHandler;
import io.lightflame.bootstrap.LightFlame;
import io.lightflame.bootstrap.FlameMock;
import org.junit.Test;

public class InputCommandTest {

    private LightFlame mock = new FlameMock();

    @Test
    public void firstTest() throws Exception{
        CommandHandler ch = new CommandHandler();
//        ch.inCommand(mock).apply(new FlameWsContextMock("open port 8080/tcp"));
//        ch.inCommand(mock).apply(MockWsContext("close port 8080"));
    }
}
