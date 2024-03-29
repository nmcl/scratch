public class Verifier
{
    public static final String EXAMPLE_1 = "example1.txt";
    public static final Integer[] EXAMPLE_1_RESULT = {0, 3, 6, 9, 2, 5, 8, 1, 4, 7};
    public static final String EXAMPLE_2 = "example2.txt";
    public static final Integer[] EXAMPLE_2_RESULT = {3, 0, 7, 4, 1, 8, 5, 2, 9, 6};
    public static final String EXAMPLE_3 = "example3.txt";
    public static final Integer[] EXAMPLE_3_RESULT = {6, 3, 0, 7, 4, 1, 8, 5, 2, 9};

    public static final Integer[] DEALT_INTO_RESULT = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    public static final Integer[] POSITIVE_CUT_RESULT = {3, 4, 5, 6, 7, 8, 9, 0, 1, 2};
    public static final Integer[] NEGATIVE_CUT_RESULT = {6, 7, 8, 9, 0, 1, 2, 3, 4, 5};
    public static final Integer[] DEAL_WITH_INCREMENT = {0, 7, 4, 1, 8, 5, 2, 9, 6, 3};

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        if (verifyBasic())
        {
            Dealer theDealer = new Dealer(EXAMPLE_1, _debug);
            Deck theDeck = theDealer.dealCards(10);
            Deck theResult = new Deck(EXAMPLE_1_RESULT, _debug);

            if (_debug)
                System.out.println("\nResult of "+EXAMPLE_1+" is "+theDeck+"\n");
            
            if (theDeck.equals(theResult))
            {
                theDealer = new Dealer(EXAMPLE_2, _debug);
                theDeck = theDealer.dealCards(10);
                theResult = new Deck(EXAMPLE_2_RESULT, _debug);

                if (_debug)
                    System.out.println("\nResult of "+EXAMPLE_2+" is "+theDeck+"\n");

                if (theDeck.equals(theResult))
                {
                    theDealer = new Dealer(EXAMPLE_3, _debug);
                    theDeck = theDealer.dealCards(10);
                    theResult = new Deck(EXAMPLE_3_RESULT, _debug);

                    if (_debug)
                        System.out.println("\nResult of "+EXAMPLE_3+" is "+theDeck+"\n");

                    if (theDeck.equals(theResult))
                        return true;
                }
            }
        }

        return false;
    }

    private boolean verifyBasic ()
    {
        Deck theDeck = new Deck(10, _debug);

        //theDeck.populateWithCards();

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

        Deck cutDeck = new Deck(POSITIVE_CUT_RESULT, _debug);

        if (theDeck.equals(cutDeck))
            System.out.println("Cut with positive worked!");
        else
        {
            System.out.println("Cut with positive did not work!");

            return false;
        }

        theDeck.populateWithCards();

        theDeck.cut(-4);

        System.out.println("\nDeck after cut -4:\n"+theDeck);

        cutDeck = new Deck(NEGATIVE_CUT_RESULT, _debug);

        if (theDeck.equals(cutDeck))
            System.out.println("Cut with negative worked!");
        else
        {
            System.out.println("Cut with negative did not work!");

            return false;
        }

        theDeck.populateWithCards();

        Table theTable = new Table(_debug);

        theTable.deal(theDeck, 3);

        theDeck = theTable.collectCards();

        System.out.println("\nDeck after deal with increment 3:\n"+theDeck);
        
        Deck dealtDeck = new Deck(DEAL_WITH_INCREMENT, _debug);

        if (theDeck.equals(dealtDeck))
            System.out.println("Deal with increment worked!");
        else
        {
            System.out.println("Deal with increment did not work!");

            return false;
        }

        return true;
    }

    private boolean _debug;
}