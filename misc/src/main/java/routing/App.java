package routing;

import io.lightflame.bootstrap.LightFlame;

public class App 
{
    public static void main( String[] args )
    {
        
        new LightFlame()
            .addBasicLog4jConfig()
            .addHttpAndWsListener(8080)
            .addConfiguration(new HandlerConfig().setDefautHandlers(), null)
            .start(App.class);
    }
}
