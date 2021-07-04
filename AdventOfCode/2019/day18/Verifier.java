import java.util.Vector;

public class Verifier
{
    private static final String EXAMPLE1_FILE = "example1.txt";
    private static final int EXAMPLE1_STEPS = 8;
    private static final String EXAMPLE2_FILE = "example2.txt";
    private static final int EXAMPLE2_STEPS = 86;
    private static final String EXAMPLE3_FILE = "example3.txt";
    private static final int EXAMPLE3_STEPS = 132;
    private static final String EXAMPLE4_FILE = "example4.txt";
    private static final int EXAMPLE4_STEPS = 136;
    private static final String EXAMPLE5_FILE = "example5.txt";
    private static final int EXAMPLE5_STEPS = 81;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<String> input = Util.readMap(EXAMPLE1_FILE);
        Map theMap = new Map(input, _debug);

        System.out.println(theMap);

        Explorer exp = new Explorer(theMap, _debug);

        System.out.println("Number of keys and doors: "+theMap.numberOfKeys()+", "+theMap.numberOfDoors());

        System.out.println("\nTraversing map ...");
        
        int steps = exp.findAllKeys();

        System.out.println("Number of steps to find all keys: "+steps);
        
        return false;
    }

    private boolean _debug;
}