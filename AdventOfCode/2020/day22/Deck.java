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

    @Override
    public String toString ()
    {
        String str = "Player "+_player+":\n";
        Iterator<Integer> iter = _theDeck.iterator();

        while (iter.hasNext())
        {
            str += iter.next()+"\n";
        }

        return str;
    }

    private int _player;
    private ArrayDeque<Integer> _theDeck;
}