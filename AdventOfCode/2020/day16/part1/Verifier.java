import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";

    public static final Ticket OWN_TICKET = new Ticket(new String[] { "7" ,"1" ,"14" });
    public static final Ticket TICKET_1 = new Ticket(new String[] { "40" ,"4" ,"50" });
    public static final Ticket TICKET_2 = new Ticket(new String[] { "55" ,"2" ,"20" });
    public static final Ticket TICKET_3 = new Ticket(new String[] { "38" ,"6" ,"12" });

    public static final int EXAMPLE_ERROR_RATE = 71;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<Category> cats = Util.loadCategories(EXAMPLE_DATA, _debug);
        Vector<Ticket> ticks = Util.loadTickets(EXAMPLE_DATA, _debug);

        if (_debug)
        {
            for (int i = 0; i < cats.size(); i++)
                System.out.println(cats.elementAt(i));

            for (int j = 0; j < ticks.size(); j++)
                System.out.println(ticks.elementAt(j));
        }

        int errorRate = 0;

        for (int k = 0; k < ticks.size(); k++)
        {
            int[] values = null;
            int index;

            if (ticks.elementAt(k).equals(OWN_TICKET))
            {
                values = Util.checkAlCategories(cats, OWN_TICKET);

                for (index = 0; index < values.length; index++)
                {
                    if (values[index] != Util.CHECKED_NUMBER)
                        errorRate += values[index];
                }
            }

            if (ticks.elementAt(k).equals(TICKET_1))
            {
                values = Util.checkAlCategories(cats, TICKET_1);

                for (index = 0; index < values.length; index++)
                {
                    if (values[index] != Util.CHECKED_NUMBER)
                        errorRate += values[index];
                }
            }

            if (ticks.elementAt(k).equals(TICKET_2))
            {
                values = Util.checkAlCategories(cats, TICKET_2);

                for (index = 0; index < values.length; index++)
                {
                    if (values[index] != Util.CHECKED_NUMBER)
                        errorRate += values[index];
                }
            }

            if (ticks.elementAt(k).equals(TICKET_3))
            {
                values = Util.checkAlCategories(cats, TICKET_3);

                for (index = 0; index < values.length; index++)
                {
                    if (values[index] != Util.CHECKED_NUMBER)
                        errorRate += values[index];
                }
            }
        }

        return (errorRate == EXAMPLE_ERROR_RATE);
    }

    private boolean _debug;
}