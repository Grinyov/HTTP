import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.concurrent.Callable;

/**
 * Created by green on 28.03.2015.
 */
//@Pattern("Thread-per-Request)

public class BlockingHttpHandler implements Callable
{
    private final Socket socket;

    public BlockingHttpHandler(Socket socket)
    {
        this.socket = socket;
    }

    public Object call() throws IOException
    {
        // read
        String httpRequset = HttpUtils.readRequest(socket.getInputStream());
        System.out.println("-----------------------------------");
        System.out.println(httpRequset);

        // process

        String httpResponse = HttpUtils.wrapConnectionClose("Hello");
        System.out.println(httpResponse);

        // write

        Writer writer = new OutputStreamWriter(socket.getOutputStream(), "ISO-8859-1");
        writer.write(httpResponse);
        writer.flush();
        return null;
    }
}
