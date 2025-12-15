import java.util.*;
import java.io.*;

public class Util
{
    public static Vector<Crab> loadCrabs (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Crab> results = new Vector<Crab>();
        
        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            String[] numbers = line.split(",");

            for (int i = 0; i < numbers.length; i++)
                results.add(new Crab(Integer.parseInt(numbers[i])));
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (Throwable ex)
            {
            }
        }

        return results;
    }
}