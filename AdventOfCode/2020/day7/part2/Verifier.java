public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";
    public static final int NUMBER_OF_BAGS = 4;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Rules theRules = new Rules(_debug);
        Inventory inv = theRules.parse(EXAMPLE_FILE);

        if (_debug)
            System.out.println("\nLoaded rules:\n\n"+inv);

        int count = inv.bagCount(LuggageProcessing.BAG_TYPE);

        if (count == NUMBER_OF_BAGS)
            return true;
        else
        {
            System.out.println("Invalid number of bags: "+count);

            return false;
        }
    }

    private boolean _debug;
}