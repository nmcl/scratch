import java.util.*;
import java.io.*;

public class Util
{
    public static final Vector<Rule> loadRules (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Rule> values = new Vector<Rule>();

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            
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

        return values;
    }

    private Util ()
    {
    }
}