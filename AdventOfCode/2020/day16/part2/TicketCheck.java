import java.util.*;

public class TicketCheck
{
    public static final String DATA_FILE = "data.txt";

    public static final String FIELD = "departure";

    public static void main (String[] args)
    {
        boolean verify = false;
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-debug] [-verify] [-help]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-verify".equals(args[i]))
                verify = true;
        }

        if (verify)
        {
            Verifier v = new Verifier(debug);

            if (v.verify())
                System.out.println("Verified ok.");
            else
                System.out.println("Verify failed!");

            System.exit(0);
        }

        Vector<Category> cats = Util.loadCategories(DATA_FILE, debug);
        Vector<Ticket> ticks = Util.loadTickets(DATA_FILE, debug);
        Vector<Ticket> validTickets = Util.validTickets(cats, ticks);

        if (debug)
        {
            for (int j = 0; j < validTickets.size(); j++)
                System.out.println(validTickets.elementAt(j));
        }

        Vector<Category> results = Util.order(validTickets, cats);

        // if (_debug)
         {
             for (int i = 0; i < results.size(); i++)
                 System.out.println("Category "+results.elementAt(i)+" maps to "+i);
         }
 
    }
}