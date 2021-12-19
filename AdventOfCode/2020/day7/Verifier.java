public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Rules theRules = new Rules(_debug);
        Inventory inv = theRules.parse(EXAMPLE_FILE);

        System.out.println("Loaded rules:\n\n"+inv);

        return false;
    }

    private boolean _debug;
}