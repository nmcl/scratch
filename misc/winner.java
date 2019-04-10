import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;

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
		fileName = args[i+1];
	}

	if (fileName == null)
	{
	    System.err.println("No filename specified!");
	    System.exit(-1);
	}

	try
	{
	    List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
	    Iterator<String> iter = lines.iterator();
	    int size = lines.size();
	    Random rand = new Random();
	    
	    System.out.println("The winner is ... "+lines.get(rand.nextInt(size)));
	}
	catch (Throwable ex)
	{
	    ex.printStackTrace();
	}
    }
}
