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

        HashMap<Integer, Category> results = new HashMap<Integer, Category>();

        for (int c = 0; c < validTickets.elementAt(0).values().length; c++)
        {
            int[] firsts = new int[ticks.size() -1];
            int key = 0;

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
                    }
                }

                if (valid)
                {
                    results.put(key, cats.elementAt(k));

                    key++;
                }
            }
        }

        for (int m = 0; m < results.size(); m++)
        {
            System.out.println("Element "+m+" maps to category "+results.get(m));
        }

        return false;
    }

    private boolean _debug;
}