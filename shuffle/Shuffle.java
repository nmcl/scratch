import java.util.Random;

/**
 * 56 card deck, no Jokers.
 *
 * Shuffle cards and then draw them down.
 */

public class Shuffle
{

    public static void main (String args[]) throws Exception
    {
	boolean finished = false;
	int cardsDealt = 0;

	while (!finished)
	{
	    int theSuite = randSuite.nextInt(3)+1;
	    char theCard = NOCARD;
	    String suite = "";

	    // choose card.

	    switch (theSuite)
	    {
	    case 1:
		theCard = getCard(hearts);
		suite = "Hearts";
		break;
	    case 2:
		theCard = getCard(spades);
		suite = "Spades";
		break;
	    case 3:
		theCard = getCard(clubs);
		suite = "Clubs";
		break;
	    case 4:
		theCard = getCard(diamonds);
		suite = "Diamonds";
		break;
	    default:
		// error

		throw new Exception("Invalid card "+theSuite);
	    }

	    if (theCard != NOCARD)
	    {
		cardsDealt++;

		System.out.print("Card dealt: ");

		switch (theCard)
		{
		case 'A':
		    System.out.print("Ace");
		    break;
		case 'X':
		    System.out.print("Ten");
		    break;
		case 'J':
		    System.out.print("Jack");
		    break;
		case 'Q':
		    System.out.print("Queen");
		    break;
		case 'K':
		    System.out.print("King");
		    break;
		default:
		    System.out.print(theCard);
		    break;
		}

		System.out.print(" of "+suite);
	    }

	    if (cardsDealt == 56)
		finished = true;
	}
    }

    private static char getCard (char[] suite)
    {
	int cardsLeft = 0;
	char toReturn = NOCARD;

	for (int i = 0; i < 14; i++)
	{
	    if (suite[i] != NOCARD)
		cardsLeft++;
	}

	if (cardsLeft != 0)
	{
	    do
	    {
		int theCard = randCard.nextInt(14);
		
		toReturn = suite[theCard];

		if (toReturn != NOCARD)
		    suite[theCard] = NOCARD;
	    }
	    while (toReturn != NOCARD);
	}

	return toReturn;
    }

    private static Random randSuite = new Random();
    private static Random randCard = new Random();

    private static char[] hearts = { 'A', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'X', 'J', 'Q', 'K' };
    private static char[] spades = { 'A', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'X', 'J', 'Q', 'K' };
    private static char[] clubs = { 'A', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'X', 'J', 'Q', 'K' };
    private static char[] diamonds = { 'A', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'X', 'J', 'Q', 'K' };

    private static final char NOCARD = ' ';
}