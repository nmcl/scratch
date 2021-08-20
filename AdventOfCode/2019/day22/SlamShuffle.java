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

        Deck theDeck = new Deck(10, debug);

        theDeck.populateWithCards();

        System.out.println("Initial: "+theDeck);

        Deck copy = new Deck(10, debug);

        if (!theDeck.dealInto(copy))
            System.out.println("Dealing failed!");

        System.out.println("\nDealt into: "+copy);
    }
}