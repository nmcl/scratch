import java.util.Random;

public class Shuffle
{

    public static void main (String args[])
    {
	Random randCard = new Random();
	Random randSuite = new Random();
	boolean finished = false;

	initialiseSuite(_hearts);
	initialiseSuite(_spades);
	initialiseSuite(_clubs);
	initialiseSuite(_diamonds);

	while (!finished)
	{
	    int theSuite = randSuite.nextInt(3);
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

    private int[] _hearts = new int[13];
    private int[] _spades = new int[13];
    private int[] _clubs = new int[13];
    private int[] _diamonds = new int[13];
}