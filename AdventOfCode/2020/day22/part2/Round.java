import java.util.*;

public class Round
{
    public Round (int player, boolean debug)
    {
        _playerId = player;
        _debug = debug;
    }

    public final void addRound (int round, Deck d)
    {
        _rounds.add(round, d);
    }

    public final boolean identicalRound (Deck d)
    {
        return _rounds.contains(d);
    }

    private int _playerId;
    private Vector<Deck> _rounds;
    private boolean _debug;
}