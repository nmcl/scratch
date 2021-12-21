public class Verifier
{
    public static final String EXAMPLE1_FILE = "example1.txt";
    public static final int EXAMPLE1_TOTAL_NUMBER_OF_BAGS = 32;

    public static final String EXAMPLE2_FILE = "example2.txt";
    public static final int EXAMPLE2_TOTAL_NUMBER_OF_BAGS = 126;

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
        {
            inv = theRules.parse(EXAMPLE2_FILE);

            if (_debug)
                System.out.println("\nLoaded rules:\n\n"+inv);

            count = inv.totalBagCount(LuggageProcessing.BAG_TYPE);

            if (count == EXAMPLE2_TOTAL_NUMBER_OF_BAGS)
                return true;
            else
                System.out.println("Invalid number of total bags: "+count+" for "+EXAMPLE2_FILE);
        }
        else
            System.out.println("Invalid number of total bags: "+count+" for "+EXAMPLE1_FILE);

        return false;
    }

    private boolean _debug;
}