public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";
    public static final int EXAMPLE_INCREASES = 5;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Scanner s = new Scanner(_debug);

        if (s.increasingDepth(EXAMPLE_DATA) == EXAMPLE_INCREASES)
            return true;
        else
            return false;
    }

    private boolean _debug;
}