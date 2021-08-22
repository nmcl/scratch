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

    public Deck (Vector<Integer> cards, boolean debug)
    {
        _theDeck = cards;
        _debug = debug;
    }

    public Deck (Integer[] cards, boolean debug)
    {
        _theDeck = new Vector<Integer>();

        _theDeck.addAll(Arrays.asList(cards));
        _debug = debug;
    }

    public final int numberOfCards ()
    {
        return _theDeck.size();
    }

    public int dealFromTop ()
    {
        if (_theDeck.size() > 0)
            return _theDeck.remove(0);
        else
            return -1;
    }

    public int dealFromBottom ()
    {
        if (_theDeck.size() > 0)
            return _theDeck.remove(_theDeck.size() -1);
        else
            return -1;
    }

    public final void populateWithCards ()  // resets if called multiple times
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
        if (numberOfCards != 0)
        {
            if (Math.abs(numberOfCards) > _theDeck.size())
                return false;
            
            if (numberOfCards > 0)
                cutPositive(numberOfCards);
            else
                cutNegative(Math.abs(numberOfCards));
        }

        return true;
    }

    @Override
    public String toString ()
    {
        String str = "Deck: ";

        for (int i = 0; i < _theDeck.size(); i++)
            str += " "+_theDeck.elementAt(i);

        return str;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;
        
        if (getClass() == obj.getClass())
        {
            Deck temp = (Deck) obj;

            if (_theDeck.size() == temp._theDeck.size())
            {
                for (int i = 0; i < _theDeck.size(); i++)
                {
                    if (_theDeck.elementAt(i) != temp._theDeck.elementAt(i))
                        return false;
                }

                return true;
            }
        }

        return false;
    }

    private void cutPositive (int numberOfCards)
    {   
        if (numberOfCards < _theDeck.size())
        {
            Vector<Integer> cutDeck = new Vector<Integer>(_theDeck.size());

            for (int i = 0; i < _theDeck.size() - numberOfCards; i++)
                cutDeck.add(i, _theDeck.elementAt(numberOfCards + i));

            for (int j = 0; j < numberOfCards; j++)
                cutDeck.add(_theDeck.size() - numberOfCards + j, _theDeck.elementAt(j));

            _theDeck = cutDeck;
        }
    }

    private void cutNegative (int numberOfCards)
    {
        if (numberOfCards < _theDeck.size())
        {
            Vector<Integer> cutDeck = new Vector<Integer>(_theDeck.size());

            for (int i = 0; i < numberOfCards; i++)
                cutDeck.add(i, _theDeck.elementAt(_theDeck.size() - numberOfCards + i));

            for (int j = 0; j < _theDeck.size() - numberOfCards; j++)
                cutDeck.add(numberOfCards + j, _theDeck.elementAt(j));

            _theDeck = cutDeck;
        }
    }

    private void initialise ()
    {
        for (int i = 0; i < _theDeck.capacity(); i++)
            _theDeck.add(-1);
    }

    private Vector<Integer> _theDeck;
    private boolean _debug;
}