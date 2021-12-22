// No OPeration - it does nothing

public class NoOp extends OpCode
{
    public NoOp (int value)
    {
        super(OpCode.NOOP);

        _value = value;
    }

    public int getValue ()
    {
        return _value
    }

    @Override
    public String toString ()
    {
        return OpCode.NOOP;
    }

    private int _value;
}