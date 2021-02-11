import java.util.*;
import java.io.*;

public class Parser
{
    public static final char DELIMITER = ',';
    public static final String PRODUCES = "=>";
    public static final char SPACE = ' ';

    public Parser (boolean debug)
    {
        _debug = debug;
    }

    public void loadData (String fileName)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        String[] values = null;

        try
        {
            reader = new BufferedReader(new FileReader(fileName));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                /*
                 * Line format:
                 * 
                 * - ALWAYS start with a number
                 * - ALWAYS follow with a String
                 * - Then EITHER a comma if more than one chemical OR => to denote end of reactants
                 * - ALWAYS end in a number and a String
                 */

                 int quantitySpace = line.indexOf(SPACE);
                 int quantity = Integer.parseInt(line.substring(0, quantitySpace));
                 
                 
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
    }

    private boolean _debug;
}