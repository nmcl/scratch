public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";
    public static final int EXAMPLE_WINNER_SCORE = 291;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Deck[] decks = Util.loadRules(EXAMPLE_FILE, _debug);

        if (_debug)
        {
            System.out.println(decks[0]);
            System.out.println(decks[1]);
        }
        
        Game g = new Game(_debug);
        int score = g.play(decks);

        if (score == EXAMPLE_WINNER_SCORE)
            return true;

        System.out.println("Incorrect winning score: "+score);

        return false;
    }

    private boolean _debug;
}