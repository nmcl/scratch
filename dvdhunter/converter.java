import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.net.URL;
import java.util.Scanner;

public class converter
{
    public static final String DIRECTOR = "Director:";
    public static final String STARRING = "Starring:";
    public static final String RATING = "Rating:";
    public static final String MEDIA = "Media:";
    public static final String LANGUAGES = "Languages:";
    public static final String GENRE = "Genre:";

    public static final String OUTPUT_FILE = "library.csv";

    public static void main (String args[]) throws IOException
    {
	String path = ".";

	for (int i = 0; i < args.length; i++)
	{
	    if ("-help".equals(args[i]))
	    {
		System.out.println("converter [-path <relative path>] [-help]");
		System.exit(0);
	    }

	    if ("-path".equals(args[i]))
		path = args[i+1];
	}

	FileWriter csvWriter = new FileWriter(OUTPUT_FILE);

	File folder = new File(System.getProperty("user.dir")+"/"+path);
	File[] fileNames = folder.listFiles();

	for (File file : fileNames)
	{
	    URL url = new URL("file://"+file.getPath());

	    addFileData(url, csvWriter);
	}

	csvWriter.flush();
	csvWriter.close();
    }

    private static void addFileData (URL url, FileWriter csvWriter) throws IOException
    {
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

	// sometimes genre is there before Director

	int genreStart = result.indexOf(converter.GENRE);
	String movie = ((genreStart == -1) ? "" : result.substring(0, genreStart));

	int directorStart = result.indexOf(converter.DIRECTOR);
	if ("".equals(movie))
	{
	    movie = result.substring(0, directorStart);
	}

	int starsStart = result.indexOf(converter.STARRING);
	String director = ((starsStart == -1) ? "" : result.substring(directorStart+converter.DIRECTOR.length(), starsStart));

	int mediaStart = result.indexOf(converter.MEDIA);
	int languagesStart = result.indexOf(converter.LANGUAGES);
	String media = ((languagesStart == -1) ? "" : result.substring(mediaStart+converter.MEDIA.length(), languagesStart));

	/*
	System.out.println("Movie: "+movie);
	System.out.println("Director: "+director);
	System.out.println("Media: "+media);
	*/

	csvWriter.append(movie);
	csvWriter.append(",");
	csvWriter.append(director);
	csvWriter.append(",");
	csvWriter.append(media);
	csvWriter.append("\n");
    }

}