public class Cell
{
    public Cell (int value)
    {
        _value = value;
        _called = false;
    }

    public final int getValue ()
    {
        return _value;
    }

    public final boolean called ()
    {
        return _called;
    }

    private int _value;
    private int _called;
}