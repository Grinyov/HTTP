import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Arrays.asList;

/**
 * Created by green on 28.02.2015.
 */

public class ImageHandler implements HttpHandler
{
    public static final String FOLDER = "folder.img";
    public static final String FILE = "file.img";

    public static final String IMG_TEXT = "file:///d:/~img/text.png";
    public static final String IMG_FOLDER = "file:///d:/~img/folder.png";

    @Override
    public void handle(HttpExchange exchange) throws IOException
    {
        try
        {
            String requestURI = exchange.getRequestURI().toString();
            System.out.println("requestURI: " + requestURI);

            String fileName = IMG_TEXT;
            if (requestURI.endsWith(FOLDER))
            {
                fileName = IMG_FOLDER;
            }

            exchange.sendResponseHeaders(200, new File(fileName).length());
            exchange.getResponseHeaders().put("Content-Type", asList("image/png"));

            OutputStream out = exchange.getResponseBody();
            Files.copy(Paths.get(new URI(fileName)), out);
            out.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            exchange.close();
        }
    }
}
