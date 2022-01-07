import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";

    public static final long EXAMPLE_RESULT = 1716;

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

        HashMap<Integer, Integer> result = Util.order(validTickets, cats);

        int fieldCount = 6;
        long value = 1;
        
        for (int i = 0; i < validTickets.elementAt(0).values().length; i++)
        {
            if (result.get(i) < fieldCount)
                value *= (long) validTickets.elementAt(0).values()[i];            
        }

        return (value == EXAMPLE_RESULT);
    }

    private boolean _debug;
}