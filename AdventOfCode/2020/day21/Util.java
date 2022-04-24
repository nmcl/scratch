import java.util.*;
import java.io.*;

public class Util
{
    public static final String ALLERGEN_DELIMITER = "(contains";

    public static final Food[] loadRules (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Food> values = new Vector<Food>();

        try
        {
            String line = null;

            reader = new BufferedReader(new FileReader(inputFile));
         
            while ((line = reader.readLine()) != null)
            {
                String[] foodData = line.split(" ");
                
                System.out.println(foodData);
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

        return null;
    }

    private Util ()
    {
    }
}