public class SlamShuffle
{
    public static final String DATA_FILE = "data.txt";

    public static void main (String[] args)
    {
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-debug] [-help]");

                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;
        }

        Deck theDeck = new Deck(debug);

        System.out.println("Initial: "+theDeck);

        System.out.println("next");
        
        Deck copy = new Deck(debug, false);

        theDeck.dealInto(copy);

        System.out.println("Dealt into: "+copy);
    }
}