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
        System.out.println("On turn "+turn+" spoken "+this);

        _nextRecentTurn = _recentTurn;
        _recentTurn = turn;
        _numberOfTimesSpoken++;

        System.out.println("Now on turn "+turn+" spoken "+this);
    }

    public Integer turnDifference ()
    {
        System.out.println("Turn diff: "+(_recentTurn - _nextRecentTurn));

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