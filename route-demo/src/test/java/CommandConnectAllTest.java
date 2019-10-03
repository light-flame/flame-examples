import demo.domain.CommandConnectAll;
import io.lightflame.bootstrap.FlameMock;
import org.junit.Test;

public class CommandConnectAllTest {

    @Test
    public void testConnectAll(){
        new CommandConnectAll("connect 8090 function{hello+world} method{get} uri{/api/test}", new FlameMock());
    }
}
