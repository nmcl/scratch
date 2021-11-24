public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";

    public static final int NUMBER_OF_TREES = 7;

    public Verifier (boolean debug)
    {
        _theMap = new Map(EXAMPLE_FILE, debug);
        _debug = debug;
    }

    public boolean verify ()
    {
        System.out.println("loaded\n"+_theMap);

        return false;
    }

    private Map _theMap;
    private boolean _debug;
}