import java.util.*;

public class Verifier
{
    public static final String EXAMPLE1_FILE = "example1.txt";
    public static final int TOTAL_ORE_1 = 13312;
    public static final long TOTAL_FUEL_1 = 82892753L;
    public static final String EXAMPLE2_FILE = "example2.txt";
    public static final int TOTAL_ORE_2 = 180697;
    public static final long TOTAL_FUEL_2 = 5586022L;
    public static final String EXAMPLE3_FILE = "example3.txt";
    public static final int TOTAL_ORE_3 = 2210736;
    public static final long TOTAL_FUEL_3 = 460664L;

    public Verifier (boolean debug)
    {
        _debug = debug;
        _theParser = new Parser(debug);
    }

    public final boolean verify ()
    {
        Vector<Reaction> reactions = _theParser.loadData(EXAMPLE1_FILE);
        NanoRefinery factory = new NanoRefinery(reactions, _debug);
        boolean verified = false;
        long totalOre = 1000000000000L;
        int oreNeeded = factory.oreNeeded(0);
        long iterations = totalOre / oreNeeded;

        factory = new NanoRefinery(reactions, _debug);
        
        oreNeeded = factory.oreNeeded(iterations);

        System.out.println("ore consumed over "+iterations+" iterations: "+oreNeeded);

        if (oreNeeded == TOTAL_ORE_1)
        {

        }
        else
            System.out.println("Failed on "+EXAMPLE1_FILE);

        return verified;
    }

    private boolean _debug;
    private Parser _theParser;
}