public class Number
{
    public Number (Long value, Long turn)
    {
        _value = value;
        _turnLastSpoken = turn;
        _numberOfTimesSpoken = 0;
    }

    public void spoken ()
    {
        _numberOfTimesSpoken++;
    }

    public Long spokenTimes ()
    {
        return _numberOfTimesSpoken;
    }

    private Long _value;
    private Long _turnLastSpoken;
    private Long _numberOfTimesSpoken;
}