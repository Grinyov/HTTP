import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by green on 28.02.2015.
 */
public class BlockingHttpServer_single
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(81, 256);
        while (true)
        {
            System.out.println("socket listen");
            Socket socket = serverSocket.accept();
            System.out.println("socket connect");
            new BlockingHttpHandler(socket).call();
            System.out.println("socket listen");
        }
    }
}
