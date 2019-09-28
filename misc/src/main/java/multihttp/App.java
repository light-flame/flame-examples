package multihttp;

import io.lightflame.bootstrap.LightFlame;
import io.lightflame.http.BasicHttpWsListener;

public class App 
{
    public static void main( String[] args )
    {
        new LightFlame()
                .addBasicLog4jConfig()
                .addListener(new BasicHttpWsListener(8080))
                .addListener(new BasicHttpWsListener(8090))
                .start(App.class);
    }
}
