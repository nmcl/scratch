import java.util.*;

public class TicketCheck
{
    public static final String DATA_FILE = "data.txt";

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

        if (debug)
        {
            for (int i = 0; i < cats.size(); i++)
                System.out.println(cats.elementAt(i));

            for (int j = 0; j < ticks.size(); j++)
                System.out.println(ticks.elementAt(j));
        }

        int errorRate = 0;

        for (int k = 0; k < ticks.size(); k++)
        {
            int[] values = Util.checkAlCategories(cats, ticks.elementAt(k));

            for (int index = 0; index < values.length; index++)
            {
                if (values[index] != Util.CHECKED_NUMBER)
                    errorRate += values[index];
            }
        }

        System.out.println("Ticket scanning error rate: "+errorRate);
    }
}