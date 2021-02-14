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

    /*
     * 10 ORE => 10 A
     * 1 ORE => 1 B
     * 7 A, 1 B => 1 C
     * 7 A, 1 C => 1 D
     * 7 A, 1 D => 1 E
     * 7 A, 1 E => 1 FUEL
     */

    public final boolean verify ()
    {
        Vector<Reaction> reactions = _theParser.loadData(EXAMPLE1_FILE);
        NanoFactory factory = new NanoFactory(reactions, _debug);
        int oreNeeded = factory.oreNeeded();
        
        return (oreNeeded == TOTAL_ORE_1);
    }

    private boolean _debug;
    private Parser _theParser;
}