public class Number
{
    public Number (Long value, Long turn)
    {
        _value = value;
        _recentTurn = turn;
        _nextRecentTurn = 0;
    }

    public void spoken (Long turn)
    {
        _nextRecentTurn = _recentTurn;
        _recentTurn = turn;
    }

    public Long turnDifference ()
    {
        return _recentTurn - _nextRecentTurn;
    }

    private Long _value;
    private Long _recentTurn;
    private Long _nextRecentTurn;
}