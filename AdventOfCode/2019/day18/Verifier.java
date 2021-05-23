import java.util.*;

public class Verifier
{
    private static final String EXAMPLE1_FILE = "example1.txt";
    private static final String EXAMPLE2_FILE = "example2.txt";
    private static final String EXAMPLE3_FILE = "example3.txt";
    private static final String EXAMPLE4_FILE = "example4.txt";
    private static final String EXAMPLE5_FILE = "example5.txt";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<String> input = Util.readMap(EXAMPLE1_FILE);
        Map theMap = new Map(input, _debug);

        System.out.println(theMap);

        return false;
    }

    private boolean _debug;
}