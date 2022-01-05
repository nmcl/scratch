import java.util.*;
import java.io.*;

public class Util
{
    public static final String YOUR_TICKET = "your ticket:";
    public static final String NEARBY_TICKETS = "nearby tickets:";

    public static final String OR = "or";

    // read the category data and ignore ticket information.

    public static final Vector<Category> loadCategories (String inputFile, boolean debug)
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

                int r1 = Integer.parseInt(firstRange.substring(0, firstRange.indexOf("-")));
                int r2 = Integer.parseInt(firstRange.substring(firstRange.indexOf("-") +1));
                int r3 = Integer.parseInt(secondRange.substring(0, secondRange.indexOf("-")));
                int r4 = Integer.parseInt(secondRange.substring(secondRange.indexOf("-") +1));

                values.add(new Category(type, r1, r2, r3, r4, debug));
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

    // read the ticket data and ignore categories.

    public static final Vector<Ticket> loadTickets (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Ticket> values = new Vector<Ticket>();

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));

            String line = null;
            boolean ticketData = false;

            while ((line = reader.readLine()) != null)
            {
                if (YOUR_TICKET.equals(line) || ((NEARBY_TICKETS.equals(line))
                {
                    ticketData = true;
                }
                else
                {
                    if (ticketData)
                    {
                        String[] range = line.split(",");

                        Ticket t = new Ticket(range);

                        values.add(t);
                    }
                }
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