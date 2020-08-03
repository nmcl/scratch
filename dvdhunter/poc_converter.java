import java.io.IOException;
import java.io.FileWriter;
import java.net.URL;
import java.util.Scanner;

public class poc_converter
{
    public static final String DIRECTOR = "Director:";
    public static final String STARRING = "Starring:";
    public static final String RATING = "Rating:";
    public static final String MEDIA = "Media:";
    public static final String LANGUAGES = "Languages:";

    public static final String OUTPUT_FILE = "library.csv";

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
	//	System.out.println("Contents of the web page: "+result);

	int directorStart = result.indexOf(poc_converter.DIRECTOR);
	String movie = result.substring(0, directorStart);

	int starsStart = result.indexOf(poc_converter.STARRING);
	String director = result.substring(directorStart+poc_converter.DIRECTOR.length(), starsStart);

	int mediaStart = result.indexOf(poc_converter.MEDIA);
	int languagesStart = result.indexOf(poc_converter.LANGUAGES);
	String media = result.substring(mediaStart+poc_converter.MEDIA.length(), languagesStart);

	System.out.println("Movie: "+movie);
	System.out.println("Director: "+director);
	System.out.println("Media: "+media);

	FileWriter csvWriter = new FileWriter(OUTPUT_FILE);

	csvWriter.append(movie);
	csvWriter.append(",");
	csvWriter.append(director);
	csvWriter.append(",");
	csvWriter.append(media);
	csvWriter.append("\n");

	csvWriter.flush();
	csvWriter.close();
    }
}