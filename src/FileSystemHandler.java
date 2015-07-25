import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;

import static java.util.Arrays.asList;


/**
 * Created by green on 28.02.2015.
 */

public class FileSystemHandler implements HttpHandler
{

    @Override
    public void handle(HttpExchange exchange) throws IOException
    {
        try
        {
            String requestURI = exchange.getRequestURI().toString().replace("%20", " ");
            File file = new File("d:" + requestURI.substring(3));
            byte[] data;
            String contentType;
            if (file.isDirectory())
            {
                data = FileSystemUtils.renderFolder(file);
                contentType = FileSystemUtils.HTML;
            }
            else
            {
                data = FileSystemUtils.renderPage(file);
                contentType = FileSystemUtils.calcRespType(file.toString());
            }

            exchange.sendResponseHeaders(200, data.length);
            exchange.getResponseHeaders().put("Content-Type", asList("image/png"));

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
