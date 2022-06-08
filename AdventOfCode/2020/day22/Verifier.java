public class Verifier
{
    public static final String EXAMPLE_FILE = "examnple.txt";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Deck[] decks = Util.loadRules(EXAMPLE_FILE, _debug);
        
        return false;
    }

    private boolean _debug;
}