import java.util.*;
import java.io.*;

public class Util
{
    public static final String YOUR_TICKET = "your ticket:";
    public static final String NEARBY_TICKETS = "nearby tickets:";

    public static final String OR = " or ";

    public static final int CHECKED_NUMBER = -1;

    public static final int[] checkAlCategories (Vector<Category> cats, Ticket t)
    {
        int[] values = new int[t.values().length];

        for (int k = 0; k < values.length; k++)
            values[k] = t.values()[k];

        for (int i = 0; i < cats.size(); i++)
        {
            for (int j = 0; j < values.length; j++)
            {
                if (values[j] != CHECKED_NUMBER)
                {
                    if (cats.elementAt(i).valid(values[j]))
                        values[j] = CHECKED_NUMBER;
                }
            }
        }

        return values;
    }

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
            boolean finished = false;

            while (((line = reader.readLine()) != null) && !finished)
            {
                if (YOUR_TICKET.equals(line) || (NEARBY_TICKETS.equals(line))) // end
                    finished = true;
                else
                {
                    int colonIndex = line.indexOf(":");

                    if (colonIndex != -1)
                    {
                        String type = line.substring(0, colonIndex);
                        int orIndex = line.indexOf(OR);
                        String firstRange = line.substring(colonIndex +1, orIndex).trim();
                        String secondRange = line.substring(orIndex + OR.length()).trim();
                        int r1 = Integer.parseInt(firstRange.substring(0, firstRange.indexOf("-")));
                        int r2 = Integer.parseInt(firstRange.substring(firstRange.indexOf("-") +1));
                        int r3 = Integer.parseInt(secondRange.substring(0, secondRange.indexOf("-")));
                        int r4 = Integer.parseInt(secondRange.substring(secondRange.indexOf("-") +1));

                        Category cat = new Category(type, r1, r2, r3, r4, debug);

                        if (debug)
                            System.out.println("Loaded "+cat);

                        values.add(cat);
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
                if (YOUR_TICKET.equals(line) || NEARBY_TICKETS.equals(line))
                {
                    ticketData = true;
                }
                else
                {
                    if (ticketData && (line.indexOf(",") != -1))
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