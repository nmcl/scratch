import java.util.*;

public class Deck
{
    public Deck (int player)
    {
        _player = player;
        _theDeck = new ArrayDeque<Integer>();
    }

    public final void add (int value)
    {
        _theDeck.add(value);
    }

    public final int draw ()
    {
        return _theDeck.removeFirst();
    }

    public final boolean empty ()
    {
        return (_theDeck.size() == 0);
    }

    public final int score ()
    {
        int index = _theDeck.size();
        int total = 0;
        Iterator<Integer> iter = _theDeck.iterator();

        while (iter.hasNext())
        {
            total += index * iter.next();

            index--;
        }

        return total;
    }

    @Override
    public String toString ()
    {
        String str = "Player "+_player+"'s deck: ";
        Iterator<Integer> iter = _theDeck.iterator();

        while (iter.hasNext())
        {
            str += iter.next()+", ";
        }

        return str;
    }

    private int _player;
    private ArrayDeque<Integer> _theDeck;
}