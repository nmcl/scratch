import java.util.*;

public class Util
{
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