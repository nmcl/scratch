import java.util.Vector;

public class Verifier
{
    private static final String EXAMPLE1_FILE = "example1.txt";
    private static final int EXAMPLE1_STEPS = 8;
    private static final String EXAMPLE2_FILE = "example2.txt";
    private static final int EXAMPLE2_STEPS = 24;
    private static final String EXAMPLE3_FILE = "example3.txt";
    private static final int EXAMPLE3_STEPS = 32;
    private static final String EXAMPLE4_FILE = "example4.txt";
    private static final int EXAMPLE4_STEPS = 72;

    public Verifier(boolean debug)
    {
        _debug = debug;
    }

    public boolean verify()
    {
        if (check(EXAMPLE1_FILE, EXAMPLE1_STEPS))
        {
            if (check(EXAMPLE2_FILE, EXAMPLE2_STEPS))
            {
                if (check(EXAMPLE3_FILE, EXAMPLE3_STEPS))
                {
                    if (check(EXAMPLE4_FILE, EXAMPLE4_STEPS))
                        return true;
                }
            }
        }

        return false;
    }

    private boolean check (String file, int outcome)
    {
        Vector<String> input = Util.readMap(file);
        Map theMap = new Map(input, _debug);

        System.out.println(theMap);

        Explorer exp = new Explorer(theMap, _debug);

        System.out.println("Number of keys and doors: " + theMap.numberOfKeys() + ", " + theMap.numberOfDoors());

        System.out.println("\nTraversing map ...");

        int steps = exp.findAllKeys();

        if (steps != outcome)
        {
            System.out.println("Error for " + file + " - number of steps: " + steps);

            return false;
        }

        System.out.println("Verified. Number of steps to find all keys: " + steps);

        return true;
    }

    private boolean _debug;
}