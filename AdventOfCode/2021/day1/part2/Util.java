import java.util.*;
import java.io.*;

public class Util
{

    public static Vector<Integer> loadData (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Integer> results = new Vector<Integer>();

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                results.add(Integer.parseInt(line));
            }
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
    
    private Util ()
    {
    }
}