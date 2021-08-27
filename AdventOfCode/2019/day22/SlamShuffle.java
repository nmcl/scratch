public class SlamShuffle
{
    public static final String DATA_FILE = "data.txt";

    public static void main (String[] args)
    {
        boolean debug = false;
        boolean verify = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-verify] [-debug] [-help]");

                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-verify".equals(args[i]))
                verify = true;
        }

        if (verify)
        {
            Verifier theVerifier = new Verifier(debug);

            if (theVerifier.verify())
                System.out.println("\nVerified ok!");
            else
                System.out.println("\nVerify failed!");

            System.exit(0);
        }

        Dealer theDealer = new Dealer(DATA_FILE, debug);
        Deck theDeck = theDealer.dealCards(10007);

        for (int i = 0; i < 2019; i++)
            System.out.println("Card in position "+(i+1)+" is "+theDeck.dealFromTop());
    }
}