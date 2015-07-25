import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import static com.sun.net.httpserver.spi.HttpServerProvider.provider;
/**
 * Created by green on 28.02.2015.
 */
public class SunHttpServer
{
    public static void main(String[] args) throws IOException
    {
        HttpServer server = provider().createHttpServer(new InetSocketAddress(81), 256);
        server.createContext("/fs/", new FileSystemHandler());
        server.createContext("/img/", new ImageHandler());
        server.setExecutor(Executors.newCachedThreadPool());

        server.start();
    }
}
