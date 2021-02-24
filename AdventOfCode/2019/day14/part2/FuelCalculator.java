import java.util.*;

public class FuelCalculator
{
    public static final String DATA_FILE = "reactions.txt";
    public static final void main (String[] args)
    {
        boolean debug = false;
        boolean verify = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-help] [-debug] [-verify]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-verify".equals(args[i]))
                verify = true;
        }

        if (verify)
        {
            Verifier v = new Verifier(debug);

            if (v.verify())
                System.out.println("Verified ok!");
            else
                System.out.println("Verify failed!");
                
            System.exit(0);
        }

        Parser theParser = new Parser(debug);
        Vector<Reaction> reactions = theParser.loadData(DATA_FILE);
        NanoRefinery factory = new NanoRefinery(reactions, debug);
        int oreNeeded = factory.oreNeeded(0);

        System.out.println("Ore needed to create 1 fuel: "+oreNeeded);

        long totalOre = 1000000000000L;

        // try a quick division
    }
}