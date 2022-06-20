import java.util.*;

public class Round
{
    public Round (int player, boolean debug)
    {
        _playerId = player;
        _debug = debug;
    }

    private int _playerId;
    private Vector<Deck> _rounds;
    private boolean _debug;
}