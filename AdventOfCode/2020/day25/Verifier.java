public class Verifier
{
    public static final long EXAMPLE_CARD_PUBLIC_KEY = 5764801;
    public static final long EXAMPLE_DOOR_PUBLIC_KEY = 17807724;
    
    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        return false;
    }

    private boolean _debug;
}