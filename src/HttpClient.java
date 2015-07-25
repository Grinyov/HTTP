import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by green on 01.03.2015.
 */
public class HttpClient
{
    public static void main(String[] args) throws IOException
    {
        Socket socket = new Socket("ya.ru", 50);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        out.write(" GET / HTTP/1.1\n\r".getBytes());
        int c;
        while ((c = in.read()) != -1)
        {
            System.out.println((char) c);
        }
    }
}
