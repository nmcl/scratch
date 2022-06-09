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

    public final void addToBottom (int value)
    {
        _theDeck.addLast(value);
    }
    
    public final int draw ()
    {
        return _theDeck.getFirst();
    }

    public final boolean empty ()
    {
        return (_theDeck.size() == 0);
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