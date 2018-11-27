import java.util.Random;
import java.io.*;

public class winner
{
    public static void main (String[] args)
    {
	String fileName = null;

	for (int i = 0; i < args.length; i++)
	{
	    if ("-help".equals(args[i]))
	    {
		System.out.println("Usage: -file <filename> [-help]");
		System.exit(0);
	    }
	    if ("-file".equals(args[i]))
		fileName = args[i];
	}

	if (fileName == null)
	{
	    System.err.println("No filename specified!");
	    System.exit(-1);
	}

	File file = new File(fileName);
	FileInputStream fileIS = new FileInputStream(file);
	
    }
}
