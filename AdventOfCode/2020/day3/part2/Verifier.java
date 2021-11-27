public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";

    public static final int TREES_1 = 2;
    public static final int TREES_2 = 7;
    public static final int TREES_3 = 3;
    public static final int TREES_4 = 4;
    public static final int TREES_5 = 2;
    
    public static final int NUMBER_OF_TREES = 336;

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

        int multTrees = _taboggan.multiMove();

        if (multTrees == NUMBER_OF_TREES)
            return true;
        else
            return false;
    }

    private Map _theMap;
    private Taboggan _taboggan;
    private boolean _debug;
}