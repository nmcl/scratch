import java.util.*;
import java.io.*;

public class Util
{
    public static char RANGE_DELIMITER = '-';
    public static char PASSWORD_DELIMITER = ':';
    public static char SPACE = ' ';

    public static Vector<PasswordData> loadData (String inputFile)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<PasswordData> results = new Vector<PasswordData>();

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                /*
                 * Parse the line just read ...
                 *
                 * <min>-<max> <letter>: <password>
                 * 
                 * Example: 5-10 b: bhbjlkbbbbbbb
                 */

                int rangeDelimiter = line.indexOf(RANGE_DELIMITER);
                int space = line.indexOf(SPACE);
                String minimum = line.substring(0, rangeDelimiter);
                String maximum = line.substring(rangeDelimiter +1, space);

                System.out.println("got "+minimum+" and "+maximum);
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