import java.util.*;

/*
 * Represents the table onto which we can deal cards. Cam also
 * pick up cards from table if there are any present.
 */

public class Table
{
    public Table (boolean debug)
    {
        _theTable = null;
        _debug = debug;
    }

    public boolean deal (Deck theDeck, int increment)
    {
        int numberOfCards = theDeck.numberOfCards();
        int index = 0;

        initialise(theDeck);

        for (int i = 0; i < numberOfCards; i++)
        {
            if (_theTable.get(index) != -1)
                return false;

            _theTable.set(index, theDeck.dealFromTop());

            index += increment;

            if (index >= numberOfCards)
                index -= numberOfCards;
        }

        return true;
    }

    public Deck collectCards ()
    {
        if (_theTable == null)
            return null;

        Deck toReturn = new Deck(_theTable, _debug);

        _theTable = null; // make sure cards are only in one place at a time.

        return toReturn;
    }

    private void initialise (Deck theDeck)
    {
        _theTable = new Vector<Integer>(theDeck.numberOfCards());

        for (int i = 0; i < _theTable.capacity(); i++)
            _theTable.add(-1);
    }

    private Vector<Integer> _theTable;
    private boolean _debug;
}