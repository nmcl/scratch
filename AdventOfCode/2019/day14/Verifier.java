import java.util.*;
import java.io.*;

public class Verifier
{
    public static final String EXAMPLE1_FILE = "example1.txt";
    public static final int TOTAL_ORE_1 = 31;
    public static final String EXAMPLE2_FILE = "example2.txt";
    public static final int TOTAL_ORE_2 = 165;
    public static final String EXAMPLE3_FILE = "example3.txt";
    public static final int TOTAL_ORE_3 = 13312;
    public static final String EXAMPLE4_FILE = "example4.txt";
    public static final int TOTAL_ORE_4 = 180697;
    public static final String EXAMPLE5_FILE = "example5.txt";
    public static final int TOTAL_ORE_5 = 2210736;

    public Verifier (boolean debug)
    {
        _debug = debug;

        _theParser = new Parser(debug);
    }

    public final boolean verify ()
    {
        Vector<Reaction> reactions = _theParser.loadData(EXAMPLE1_FILE);

        //if (_debug)
        {
            Enumeration<Reaction> iter = reactions.elements();

            while (iter.hasMoreElements())
                System.out.println(iter.nextElement());
        }

        return false;
    }

    private Reaction findReaction (String name, Vector<Reaction> reactions)
    {
        Enumeration<Reaction> iter = reactions.elements();

        while (iter.hasMoreElements())
        {
            Reaction r = iter.nextElement();

            if (r.name().equals(name))
                return r;
        }

        return null;
    }

    private boolean _debug;
    private Parser _theParser;
}