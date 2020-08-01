import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class converter
{
    static final String DIRECTOR = "Director";
    static final String STARRING = "Starring";
    static final String MEDIA = "Media";

    public static void main (String args[]) throws IOException
    {
	String fileName = "FF74D200-9E9B-4A7C-8A58-70CEAA7FF488.html";

	URL url = new URL("file://"+System.getProperty("user.dir")+"/"+fileName);
	Scanner sc = new Scanner(url.openStream());
	StringBuffer sb = new StringBuffer();

	while (sc.hasNext())
	{
	    sb.append(sc.next());
	}

	String result = sb.toString();

	// Removing the HTML tags

	result = result.replaceAll("<[^>]*>", "");
	System.out.println("Contents of the web page: "+result);
    }
}