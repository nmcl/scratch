import java.util.*;

/*
 * Represents a single deck of cards. Initially the deck
 * is empty until populated.
 */

public class Deck
{
    public static final int SIZE_OF_SPACE_CARDS_DECK = 10007;

    public Deck ()
    {
        this(SIZE_OF_SPACE_CARDS_DECK, false);
    }

    public Deck (int deckSize, boolean debug)
    {
        _theDeck = new Vector<Integer>(deckSize);
        _debug = debug;

        initialise();
    }

    public final void populateWithCards ()
    {
        for (int i = 0; i < _theDeck.capacity(); i++)
        {
            _theDeck.set(i, i);
        }
    }

    public boolean dealInto (Deck recipient)
    {
        if (_theDeck.size() != recipient._theDeck.size())
            return false;

        for (int i = 0; i < _theDeck.capacity(); i++)
        {
            recipient._theDeck.set(_theDeck.capacity() -1 -i, _theDeck.elementAt(i));
        }

        return true;
    }

    public String toString ()
    {
        String str = "Deck: ";

        for (int i = 0; i < _theDeck.size(); i++)
            str += "\n"+_theDeck.elementAt(i);

        return str;
    }

    private void initialise ()
    {
        for (int i = 0; i < _theDeck.capacity(); i++)
            _theDeck.add(-1);
    }

    private Vector<Integer> _theDeck;
    private boolean _debug;
}