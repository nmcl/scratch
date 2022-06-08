import java.util.*;

public class Deck
{
    public Deck ()
    {
        _theDeck = new ArrayDeque<Integer>();
    }

    public final void add (int value)
    {
        _theDeck.add(value);
    }

    @Override
    public String toString ()
    {
        String str = "";
        Iterator<Integer> iter = _theDeck.iterator();

        while (iter.hasNext())
        {
            str += iter.next();
        }

        return str;
    }

    private ArrayDeque<Integer> _theDeck;
}