public class Verifier
{
    public static final String EXAMPLE1_FILE = "example1.txt";
    public static final int EXAMPLE1_TOTAL_NUMBER_OF_BAGS = 32;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Rules theRules = new Rules(_debug);
        Inventory inv = theRules.parse(EXAMPLE1_FILE);

        if (_debug)
            System.out.println("\nLoaded rules:\n\n"+inv);

        int count = inv.totalBagCount(LuggageProcessing.BAG_TYPE);

        if (count == EXAMPLE1_TOTAL_NUMBER_OF_BAGS)
            return true;
        else
        {
            System.out.println("Invalid number of total bags: "+count);

            return false;
        }
    }

    private boolean _debug;
}