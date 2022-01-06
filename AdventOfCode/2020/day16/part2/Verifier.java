import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";

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

        for (int i = 1; i < validTickets.size(); i++)
        {
            int[] values = validTickets.elementAt(i).values();
            int number = 0;

            for (int k = 0; k < cats.size(); k++)
            {
                for (int l = 0; l < values.length; l++)
                {
                    if (!cats.elementAt(k).valid(values[l]))
                    {
                        number++;
                        break;
                    }
                }
            }
            
            System.out.println("Ticket "+validTickets.elementAt(i)+" matches "+number+" categories.");
        }

        return false;
    }

    private boolean _debug;
}