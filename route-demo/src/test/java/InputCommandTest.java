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
        assert(srv.exec().apply("connect 8090 f(hello+world) h(method(post) uri(/api/test)) ").startsWith("new connection on port 8090"));
        // connect 8090 -> f(hello+world) -> h(post /api/test) ->
        assert(srv.exec().apply("close port 8090").startsWith("port 8090 closed successfully"));
    }
}
