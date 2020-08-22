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

	    // choose card.

	    switch (theSuite)
	    {
	    case 1:
		theCard = getCard(_hearts);
		break;
	    case 2:
		theCard = getCard(_spades);
		break;
	    case 3:
		theCard = getCard(_clubs);
		break;
	    case 4:
		theCard = getCard(_clubs);
		break;
	    default:
		// error

		throw new Exception("Invalid card "+theSuite);
	    }

	    if (theCard != NOCARD)
		cardsDealt++;

	    if (cardsDealt == 56)
		finished = true;
	}
    }

    private char getCard (char[] suite)
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

    private Random _randSuite = new Random();
    private Random randCard = new Random();

    private char[] _hearts = { 'A', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'X', 'J', 'Q', 'K' };
    private char[] _spades = { 'A', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'X', 'J', 'Q', 'K' };
    private char[] _clubs = { 'A', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'X', 'J', 'Q', 'K' };
    private char[] _diamonds = { 'A', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'X', 'J', 'Q', 'K' };

    private static final char NOCARD = '';
}