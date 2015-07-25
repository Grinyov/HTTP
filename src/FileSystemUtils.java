import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by green on 01.03.2015.
 */
public class FileSystemUtils
{
    public static final String HTML = "text/html; charset=cp1251";

    public static String calcRespType(String pathName)
    {
        // text
        if (pathName.endsWith(".xml"))
        {
            return "application/xml";
        }
        else if (pathName.endsWith(".pdf"))
        {
            return "application/pdf";
        }
        else if (pathName.endsWith(".zip"))
        {
            return "application/zip";
        }
        else if (pathName.endsWith(".txt")) {
            return "application/plain";
        }

        return "";
    }

    public static byte[] renderPage(File file) throws IOException
    {
        ByteArrayOutputStream buff = new ByteArrayOutputStream(64 * 1024);
        Files.copy(Paths.get(file.toURI()), buff);
        return buff.toByteArray();
    }

    public static byte[] renderFolder(File root) throws IOException
    {
        StringBuilder result = new StringBuilder(8 * 1024);
        result.append("<html><body>");

        // ...

        File[] files = root.listFiles();
        if (root.getParentFile() != null )
        {
            addFileLine(result, root.getParentFile(), "...");
        }

        // folder content

        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File file0, File file1) {
                return ((file0.isDirectory() ? 0 : 1)) - ((file1.isDirectory() ? 0 : 1));
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        });

        for (File file : files)
        {
            addFileLine(result, file, file.getName());
        }

        result.append("</body></html>");
        return result.toString().getBytes("cp1251");
    }

    private static void addFileLine(StringBuilder result, File file, String caption)
    {
        result.append("\n");

        if (caption.equals("..."))
        {
            // NOP
        }
        else if (file.isDirectory())
        {
            result.append("<br><img src>=\"/img/folder/folder.img\"/>&nbsp");
        }
        else
        {
            result.append("<br><img src>=\"/img/folder/folder.img\"/>&nbsp");
        }
        result.append("<a href=\"");
        File absoluteFile = file.getAbsoluteFile();
        result.append("/fs/" + absoluteFile.toString().substring(3));
        result.append("\">");
        result.append(caption);
        result.append("/a>");
    }
}
