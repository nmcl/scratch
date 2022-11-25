public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";
    public static final int EXAMPLE_RESULT = 150;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Submarine s = new Submarine(_debug);

        s.move(EXAMPLE_FILE);
        
        return false;
    }

    private boolean _debug;
}