public class LuggageProcessing
{
    public static final Bag BAG_TYPE = new Bag("shiny gold");

    public static final String DATA_FILE = "input.txt";

    public static void main (String[] args)
    {
        boolean debug = false;
        boolean verify = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-debug] [-verify] [-help]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-verify".equals(args[i]))
                verify = true;
        }

        if (verify)
        {
            Verifier v = new Verifier(debug);

            if (v.verify())
                System.out.println("Verified ok.");
            else
                System.out.println("Verify failed!");

            System.exit(0);
        }

        Rules theRules = new Rules(debug);
        Inventory inv = theRules.parse(DATA_FILE);

        if (debug)
            System.out.println("Loaded rules:\n\n"+inv);

        int count = inv.totalBagCount(BAG_TYPE);

        System.out.println("Number of individual bags required inside single "+BAG_TYPE+": "+count);
    }
}