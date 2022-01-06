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

        Vector<Category> results = new Vector<Category>();

        for (int c = 0; c < validTickets.elementAt(0).values().length; c++)
        {
            System.out.println();

            int[] firsts = new int[ticks.size() -1];

            for (int i = 0; i < firsts.length; i++)
                firsts[i] = ticks.elementAt(i+1).values()[c];

            for (int k = 0; k < cats.size(); k++)
            {
                boolean valid = true;

                for (int l = 0; l < firsts.length; l++)
                {
                    if (cats.elementAt(k).valid(firsts[l]))
                    {
                        System.out.println("Category "+cats.elementAt(k)+" is valid for "+firsts[l]);
                    }
                    else
                    {
                        System.out.println("Category "+cats.elementAt(k)+" is invalid for "+firsts[l]);

                        valid = false;
                        break;
                    }
                }

                if (valid)
                {
                    results.add(cats.elementAt(k));

                    if (!results.contains(cats.elementAt(k)))
                        results.add(cats.elementAt(k));

                    key++;
                }
            }
        }

        System.out.println();

        for (int m = 0; m < results.size(); m++)
        {
            System.out.println("Element "+m+" maps to category "+results.elementAt(m));
        }

        return false;
    }

    private boolean _debug;
}