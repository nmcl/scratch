import java.util.*;
import java.io.*;

public class Util
{

    public static Vector<Command> loadData (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Command> results = new Vector<Command>();

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                int split = line.indexOf(" ");
                String cmd = line.substring(0, split);
                int amount = Integer.parseInt(line.substring(split+1));

                if (debug)
                    System.out.println("Loaded "+cmd+" "+amount);

                results.add(new Command(cmd, amount));
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