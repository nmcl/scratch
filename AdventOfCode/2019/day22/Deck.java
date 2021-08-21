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

    public boolean cut (int numberOfCards)
    {
        if (numberOfCards < 0)
            return false; // for now!

        if (numberOfCards > _theDeck.size())
            return false;
        
        if (numberOfCards < _theDeck.size())
        {
            Vector<Integer> cutDeck = new Vector<Integer>(_theDeck.size());

            for (int i = 0; i < _theDeck.size() - numberOfCards; i++)
                cutDeck.set(i, _theDeck.elementAt(numberOfCards + i));

            for (int j = 0; j < numberOfCards; j++)
                cutDeck.set(j + numberOfCards, _theDeck.elementAt(j));
        }

        return true;
    }

    public String toString ()
    {
        String str = "Deck: ";

        for (int i = 0; i < _theDeck.size(); i++)
            str += " "+_theDeck.elementAt(i);

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