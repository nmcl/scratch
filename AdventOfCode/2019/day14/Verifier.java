import java.util.*;

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
        NanoRefinery factory = new NanoRefinery(reactions, _debug);
        int oreNeeded = factory.oreNeeded();
        boolean verified = false;

        System.out.println("**ORE "+oreNeeded);
        
        if (oreNeeded == TOTAL_ORE_1)
        {
            System.out.println("Verified "+EXAMPLE1_FILE);

            reactions = _theParser.loadData(EXAMPLE2_FILE);
            factory = new NanoRefinery(reactions, _debug);
            oreNeeded = factory.oreNeeded();

            if (oreNeeded == TOTAL_ORE_2)
            {
                System.out.println("Verified "+EXAMPLE2_FILE);

                reactions = _theParser.loadData(EXAMPLE3_FILE);
                factory = new NanoRefinery(reactions, _debug);
                oreNeeded = factory.oreNeeded();

                if (oreNeeded == TOTAL_ORE_3)
                {
                    System.out.println("Verified "+EXAMPLE3_FILE);

                    reactions = _theParser.loadData(EXAMPLE4_FILE);
                    factory = new NanoRefinery(reactions, _debug);
                    oreNeeded = factory.oreNeeded();

                    if (oreNeeded == TOTAL_ORE_4)
                        verified = true;
                }
                else
                    System.out.println("Failed on "+EXAMPLE3_FILE);
            }
            else
                System.out.println("Failed on "+EXAMPLE2_FILE);
        }
        else
            System.out.println("Failed on "+EXAMPLE1_FILE);

        return verified;
    }

    private boolean _debug;
    private Parser _theParser;
}