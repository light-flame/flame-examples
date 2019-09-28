package routing;

import io.lightflame.bootstrap.LightFlame;
import io.lightflame.http.BasicHttpWsListener;

public class App 
{
    public static void main( String[] args )
    {
        
        new LightFlame()
            .addBasicLog4jConfig()
            .addListener(new BasicHttpWsListener(8080))
            .addConfiguration(new HandlerConfig().setDefautHandlers(), null)
            .start(App.class);
    }
}
