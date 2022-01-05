import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";

    public static final Ticket OWN_TICKET = new Ticket(new String[] { "7" ,"1" ,"14" });
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

        boolean[] correct = new boolean[4];

        for (int l = 0; l < 4; l++)
            correct[l] = false;

        for (int k = 0; k < ticks.size(); k++)
        {
            if (ticks.elementAt(k).equals(OWN_TICKET) && (Util.checkAlCategories(cats, OWN_TICKET)))
                correct[0] = true;

            if (ticks.elementAt(k).equals(TICKET_1) && (!Util.checkAlCategories(cats, TICKET_1)))
                correct[1] = true;

            if (ticks.elementAt(k).equals(TICKET_2) && (!Util.checkAlCategories(cats, TICKET_2)))
                correct[2] = true;

            if (ticks.elementAt(k).equals(TICKET_3) && (!Util.checkAlCategories(cats, TICKET_3)))
                correct[3] = true;
        }

        return correct[0] & correct[1] & correct[2] & correct[3];
    }

    private boolean _debug;
}