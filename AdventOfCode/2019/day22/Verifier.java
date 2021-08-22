public class Verifier
{
    public static final String EXAMPLE_1 = "example1.txt";
    public static final Integer[] EXAMPLE_1_RESULT = {0, 3, 6, 9, 2, 5, 8, 1, 4, 7};
    public static final String EXAMPLE_2 = "example2.txt";
    public static final Integer[] EXAMPLE_2_RESULT = {3, 0, 7, 4, 1, 8, 5, 2, 9, 6};
    public static final String EXAMPLE_3 = "example3.txt";
    public static final Integer[] EXAMPLE_3_RESULT = {6, 3, 0, 7, 4, 1, 8, 5, 2, 9};

    public static final Integer[] DEALT_INTO_RESULT = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    public static final Integer[] CUT_RESULT = {3, 4, 5, 6, 7, 8, 9, 0, 1, 2};

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

        Deck dealtInto = new Deck(DEALT_INTO_RESULT, _debug);

        if (copy.equals(dealtInto))
            System.out.println("Dealt into worked!");
        else
        {
            System.out.println("Dealt into did not work!");

            return false;
        }

        theDeck.cut(3);

        System.out.println("\nDeck after cut 3:\n"+theDeck);

        Deck cutDeck = new Deck(CUT_RESULT, _debug);

        if (theDeck.equals(cutDeck))
            System.out.println("Cut worked!");
        else
        {
            System.out.println("Cut did not work!");

            return false;
        }
        
        theDeck.populateWithCards();

        theDeck.cut(-4);

        System.out.println("\nDeck after cut -4:\n"+theDeck);

        theDeck.populateWithCards();

        Table theTable = new Table(_debug);

        theTable.deal(theDeck, 3);

        theDeck = theTable.collectCards();

        System.out.println("\nDeck after deal with increment 3:\n"+theDeck);
        
        return true;
    }

    private boolean _debug;
}