import java.util.Random;

/**
 * 52 card deck, no Jokers.
 *
 * Shuffle cards and then draw them down.
 */

public class Shuffle
{

    public static void main (String args[])
    {
	boolean finished = false;

	initialiseSuite(_hearts);
	initialiseSuite(_spades);
	initialiseSuite(_clubs);
	initialiseSuite(_diamonds);

	while (!finished)
	{
	    int theSuite = randSuite.nextInt(3);
	    int theCard;

	    switch (theSuite)
	    {
	    case 0:
		theCard = getCard(_hearts, _heartsLeft);
		break;
	    case 1:
		theCard = getCard(_spades, _spadesLeft);
		break;
	    case 2:
		theCard = getCard(_clubs, _clubsLeft);
		break;
	    default:
		theCard = getCard(_diamonds, _diamondsLeft);
		break;
	    }
	}
    }

    private int getCard (int[] suite, int left)
    {
	if (left == 0)
	    return -1;

	for (;;)
	{
	    int theCard = randCard.nextInt(13);
	}
    }

    private void initialiseSuite (int[] suite)
    {
	for (int i = 0; i < suite.length(); i++)
	{
	    if (i == 0)
		suite[i] = 11;
	    else
	    {
		if (i >= 10)
		    suite[i] = 10;
		else
		    suite[i] = i;
	    }
	}
    }

    private Random _randSuite = new Random();
    private Random randCard = new Random();

    // hack!!

    private int[] _hearts = new int[13];
    private int _heartsLeft = 13;
    private int[] _spades = new int[13];
    private int _spadesLeft = 13;
    private int[] _clubs = new int[13];
    private int _clubsLeft = 13;
    private int[] _diamonds = new int[13];
    private int _diamondsLeft = 13;
}