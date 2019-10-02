import demo.domain.Services;
import io.lightflame.bootstrap.LightFlame;
import io.lightflame.bootstrap.FlameMock;
import org.junit.Test;

public class InputCommandTest {

    private LightFlame mock = new FlameMock();

    @Test
    public void firstTest() throws Exception{
        Services srv = new Services(new FlameMock());
        srv.exec().apply("load script hello");
        assert(srv.exec().apply("open port 8090/http").startsWith("port 8090"));
        assert(srv.exec().apply("close port 8090").startsWith("port 8090 closed successfully"));
    }
}
