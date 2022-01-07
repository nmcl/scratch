import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";

    public static final String FIRST = "row";
    public static final String SECOND = "class";
    public static final String THIRD = "seat";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<Category> cats = Util.loadCategories(EXAMPLE_DATA, _debug);
        Vector<Ticket> ticks = Util.loadTickets(EXAMPLE_DATA, _debug);
        Vector<Ticket> validTickets = Util.validTickets(cats, ticks);

        if (_debug)
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

        /*
        if (FIRST.equals(results.elementAt(0).getType()) &&
            SECOND.equals(results.elementAt(1).getType()) &&
            THIRD.equals(results.elementAt(2).getType()))
        {
            return true;
        }*/

        return false;
    }

    private boolean _debug;
}