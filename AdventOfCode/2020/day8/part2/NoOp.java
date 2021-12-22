// No OPeration - it does nothing, but might have a parameter!

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