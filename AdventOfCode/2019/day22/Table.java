import java.util.*;

/*
 * Represents the table onto which we can deal cards.
 */

public class Table
{
    public Table (boolean debug)
    {
        _debug = debug;
    }

    public boolean deal (Deck theDeck, int increment)
    {
        Vector<Integer> table = new Vector<Integer>(theDeck.numberOfCards());

        return false;
    }

    private boolean _debug;
}