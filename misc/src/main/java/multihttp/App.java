package multihttp;

import io.lightflame.bootstrap.LightFlame;

public class App 
{
    public static void main( String[] args )
    {
        new LightFlame()
                .addBasicLog4jConfig()
                .addHttpAndWsListener(8080)
                .addHttpAndWsListener(8090)
                .start(App.class);
    }
}
