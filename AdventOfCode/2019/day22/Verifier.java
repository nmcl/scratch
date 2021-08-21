public class Verifier
{
    public static final String EXAMPLE_1 = "example1.txt";
    public static final int[] EXAMPLE_1_RESULT = {0, 3, 6, 9, 2, 5, 8, 1, 4, 7};
    public static final String EXAMPLE_2 = "example2.txt";
    public static final int[] EXAMPLE_2_RESULT = {3, 0, 7, 4, 1, 8, 5, 2, 9, 6};
    public static final String EXAMPLE_3 = "example3.txt";
    public static final int[] EXAMPLE_3_RESULT = {6, 3, 0, 7, 4, 1, 8, 5, 2, 9};

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Deck theDeck = new Deck(10, _debug);

        theDeck.populateWithCards();

        System.out.println("Initial:\n"+theDeck);

        Deck copy = new Deck(10, _debug);

        if (!theDeck.dealInto(copy))
            System.out.println("Dealing failed!");

        System.out.println("\nDealt into:\n"+copy);

        theDeck.cut(3);

        System.out.println("\nDeck after cut 3:\n"+theDeck);

        return true;
    }

    private boolean _debug;
}