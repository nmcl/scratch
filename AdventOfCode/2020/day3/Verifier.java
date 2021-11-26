public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";

    public static final int NUMBER_OF_TREES = 7;

    public Verifier (boolean debug)
    {
        _theMap = new Map(EXAMPLE_FILE, debug);
        _taboggan = new Taboggan(_theMap, debug);
        _debug = debug;
    }

    public boolean verify ()
    {
        if (_debug)
            System.out.println("Verified loaded\n"+_theMap);

        int trees = _taboggan.move();

        System.out.println("Trees encountered: "+trees);
        
        return false;
    }

    private Map _theMap;
    private Taboggan _taboggan;
    private boolean _debug;
}