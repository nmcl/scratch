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

    public final void call ()
    {
        _called = true;
    }

    public final boolean called ()
    {
        return _called;
    }

    @Override
    public String toString ()
    {
        String str = "";

        if (_value < 10)
            str += " ";

        str += _value+((_called) ? "T" : "F");

        return str;
    }

    private int _value;
    private boolean _called;
}