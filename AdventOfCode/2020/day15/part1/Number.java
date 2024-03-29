public class Number
{
    public Number (Integer value, Integer turn)
    {
        _value = value;
        _recentTurn = turn;
        _nextRecentTurn = 1;
        _numberOfTimesSpoken = 1;
    }

    public void spoken (Integer turn)
    {
        _nextRecentTurn = _recentTurn;
        _recentTurn = turn;
        _numberOfTimesSpoken++;
    }

    public Integer turnDifference ()
    {
        return _recentTurn - _nextRecentTurn;
    }

    public Integer spokenTimes ()
    {
        return _numberOfTimesSpoken;
    }

    @Override
    public String toString ()
    {
        return "Number < "+_value+", "+_recentTurn+", "+_nextRecentTurn+", "+_numberOfTimesSpoken+" >";
    }

    private Integer _value;
    private Integer _recentTurn;
    private Integer _nextRecentTurn;
    private Integer _numberOfTimesSpoken;
}