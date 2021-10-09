import java.util.*;
import java.io.*;

public class Util
{
    public static final int NEWLINE = 10;

    public static String outputToString (LinkedList<String> output)
    {
        ListIterator<String> iter = output.listIterator();
        String toReturn = "";

        while (iter.hasNext())
        {
            String s = iter.next();
            int v = Integer.parseInt(s);

            toReturn += (char) v;
        }

        return toReturn;
    }

    public static String[] stringToInput (String command, String line)
    {
        Vector<String> data = new Vector<String>();

        for (int i = 0; i < command.length(); i++)
        {
            data.add(""+(int) command.charAt(i));
        }

        for (int i = 0; i < line.length(); i++)
        {
            data.add(""+(int) line.charAt(i));
        }

        System.out.println("created "+data);
        
        return (String[]) data.toArray();
    }

    public static String getInput ()
    {
        String input = "";

        try
        {
            InputStreamReader isReader = new InputStreamReader(System.in);
            BufferedReader bufferedReader = new BufferedReader(isReader);

            input = bufferedReader.readLine();
        }
        catch (Throwable ex)
        {
        }

        return input;
    }

    private Util ()
    {
    }
}