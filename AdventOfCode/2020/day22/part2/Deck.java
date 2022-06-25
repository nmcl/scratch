import java.util.*;

public class Deck
{
    public Deck (int player)
    {
        _player = player;
        _theDeck = new ArrayDeque<Integer>();
    }

    public Deck (Deck deck, int offset)
    {
        _player = deck._player;
        _theDeck = new ArrayDeque<Integer>(deck._theDeck);

        for (int i = _theDeck.size() - offset; i > 0; i--)
            _theDeck.removeLast();
    }

    public final void add (int value)
    {
        _theDeck.add(value);
    }

    public final int draw ()
    {
        return _theDeck.removeFirst();
    }

    public final int size ()
    {
        return _theDeck.size();
    }
    
    public final boolean isEmpty ()
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
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;
    
        if (getClass() == obj.getClass())
        {
            Deck temp = (Deck) obj;

            // ignore player ID for now.

            return (_theDeck.equals(temp._theDeck));
        }

        return false;
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