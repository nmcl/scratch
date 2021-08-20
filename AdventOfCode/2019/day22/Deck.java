import java.util.*;

public class Deck
{
    public static final int SIZE_OF_DECK = 10007;

    public Deck (boolean debug)
    {
        _theDeck = new Vector<Integer>(SIZE_OF_DECK);
        _debug = debug;
    }

    private Vector<Integer> _theDeck;
    private boolean _debug;
}