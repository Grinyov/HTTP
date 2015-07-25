import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by green on 28.02.2015.
 */
public class BlockingHttpServer_multi
{
    public static void main(String[] args) throws IOException
    {
        ExecutorService executor = new ThreadPoolExecutor(0, 100,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        ServerSocket serverSocket = new ServerSocket(81, 256);
        while (true)
        {
            System.out.println("socket listen");

            Socket socket = serverSocket.accept();

            System.out.println("socket connect");

            executor.submit(new BlockingHttpHandler(socket));

            System.out.println("socket listen");
        }
    }
}
