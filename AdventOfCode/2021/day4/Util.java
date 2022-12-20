import java.util.*;
import java.io.*;

public class Util
{

    public static Vector<Integer> loadNumbers (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Integer> results = new Vector<String>();
        
        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            String[] numbers = line.split(",");

            for (int i = 0; i < numbers.length; i++)
                results.add(Integer.parseInt(numbers[i]));
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
        }

        return results;
    }
}