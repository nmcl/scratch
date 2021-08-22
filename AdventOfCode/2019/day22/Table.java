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
        return false;
    }

    public Deck collectCards ()
    {
        if (_theTable == null)
            return null;

        Deck toReturn = new Deck(_theTable);

        _theTable = null; // make sure cards are only in one place at a time.

        return toReturn;
    }
    
    private Vector<Integer> _theTable;
    private boolean _debug;
}