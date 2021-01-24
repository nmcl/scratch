public class Verifier
{
    public static final String EXAMPLE1_DATA = "example1.txt";
    public static final String EXAMPLE2_DATA = "example2.txt";

    public Verifier (boolean debug)
    {
        _activator = null;
        _debug = debug;
    }

    public final boolean verify ()
    {
        boolean result = false;

        _activator = new Activate(EXAMPLE1_DATA, _debug);

        return result;
    }

    private Activate _activator;
    private boolean _debug;
}