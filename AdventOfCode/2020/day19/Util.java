import java.util.*;
import java.io.*;

public class Util
{
    public static final char RULE_NAME_DELIMITER = ':';
    public static final char OR_DELIMITER = '|';

    public static final Vector<Rule> loadRules (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Rule> values = new Vector<Rule>();

        try
        {
            String line = null;

            reader = new BufferedReader(new FileReader(inputFile));
         
            while (((line = reader.readLine()) != null) && (line.length() > 0))
            {
                int nameDelim = line.indexOf(RULE_NAME_DELIMITER);
                String name = line.substring(0, nameDelim);

                int orDelim = line.indexOf(OR_DELIMITER);  // there's only ever a maximum of one of these in a rule
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

        return values;
    }

    public static final Vector<Message> loadMessages (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Message> values = new Vector<Message>();

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