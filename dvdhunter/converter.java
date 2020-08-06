import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.net.URL;
import java.util.Scanner;

public class converter
{
    /**
     * The various fields representing the movie. Apart from the movie title, all
     * of them are optional.
     *
     * DO NOT re-order.
     */

    public static final String DIRECTOR = "Director:";
    public static final String GENRE = "Genre:";
    public static final String STARRING = "Starring:";
    public static final String RATING = "Rating:";
    public static final String MEDIA = "Media:";
    public static final String LANGUAGES = "Languages:";
    public static final String SUMMARY = "Summary:";

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
	//	System.out.println("Contents of the web page ("+url+"): "+result);

	// sometimes genre is there before Director

	int genreStart = result.indexOf(converter.GENRE);
	int directorStart = result.indexOf(converter.DIRECTOR);
	String genre = "";

	if ((genreStart != -1) && (directorStart != -1))
	    genre = result.substring(genreStart+converter.GENRE.length(), directorStart);

	String movie = ((genreStart == -1) ? "" : result.substring(0, genreStart));

	if ("".equals(movie))
	{
	    movie = ((directorStart == -1) ? "" : result.substring(0, directorStart));
	}

	int starsStart = result.indexOf(converter.STARRING);
	String director = (((starsStart == -1) || (directorStart == -1)) ? "" : result.substring(directorStart+converter.DIRECTOR.length(), starsStart));

	// Sometimes Summary comes before Languages!

	int mediaStart = result.indexOf(converter.MEDIA);
	int languagesStart = result.indexOf(converter.LANGUAGES);
	int summaryStart = result.indexOf(converter.SUMMARY);
	String media = "";

	if (languagesStart < summaryStart)
	    media = ((languagesStart == -1) ? "" : result.substring(mediaStart+converter.MEDIA.length(), languagesStart));
	else
	    media = ((summaryStart == -1) ? "" : result.substring(mediaStart+converter.MEDIA.length(), summaryStart));

	if ("".equals(movie))
	{
	    movie = ((mediaStart == -1) ? "" : result.substring(0, mediaStart));
	}

	// Do some final santity checking due to inconsistencies in output format!

	int ratingStart = movie.indexOf(converter.RATING);

	if (ratingStart != -1)
	    movie = movie.substring(0, ratingStart);

	System.out.println("Movie: "+movie);
	System.out.println("Genre: "+genre);
	System.out.println("Director: "+director);
	System.out.println("Media: "+media);

	csvWriter.append(movie);
	csvWriter.append(",");
	csvWriter.append(director);
	csvWriter.append(",");
	csvWriter.append(media);
	csvWriter.append("\n");
    }

}