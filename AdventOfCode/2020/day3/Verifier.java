public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";

    public Verifier (boolean debug)
    {
        _theMap = new Map(EXAMPLE_FILE, debug);
        _debug = debug;
    }

    public boolean verify ()
    {
        System.out.println("loaded "+_theMap);
        
        return false;
    }

    private Map _theMap;
    private boolean _debug;
}