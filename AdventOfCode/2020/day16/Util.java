import java.util.*;
import java.io.*;

public class Util
{
    public static final String YOUR_TICKET = "your ticket:";
    public static final String NEARBY_TICKETS = "nearby tickets:";

    public static final String OR = "or";

    public static final Vector<Category> loadData (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Category> values = new Vector<Category>();

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));

            String line = null;

            while ((line = reader.readLine()) != null)
            {
                if (YOUR_TICKET.equals(line) || (NEARBY_TICKETS.equals(line)))
                    break;

                int colonIndex = line.indexOf(":");
                String type = line.substring(0, colonIndex);

                int orIndex = line.indexOf(OR);
                String firstRange = line.substring(colonIndex +1, orIndex).trim();
                String secondRange = line.substring(orIndex + OR.length()).trim();
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

    private Util ()
    {
    }
}