public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";
    public static final Bag BAG_TYPE = new Bag("shiny gold");
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
            System.out.println("Loaded rules:\n\n"+inv);

        int count = inv.bagCount(BAG_TYPE);

        System.out.println("got "+count);
        
        if (count == NUMBER_OF_BAGS)
            return true;
        else
            return false;
    }

    private boolean _debug;
}