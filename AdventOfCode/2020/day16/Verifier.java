import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";

    public static final Ticket TICKET_1 = new Ticket(new String[] { "40" ,"4" ,"50" });
    public static final Ticket TICKET_2 = new Ticket(new String[] { "55" ,"2" ,"20" });
    public static final Ticket TICKET_3 = new Ticket(new String[] { "38" ,"6" ,"12" });

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

        for (int k = 0; k < ticks.size(); k++)
        {
            Util.checkAlCategories(cats, ticks.elementAt(k));
        }

        return false;
    }

    private boolean _debug;
}