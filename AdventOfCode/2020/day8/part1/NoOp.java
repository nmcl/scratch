// No OPeration - it does nothing

public class NoOp extends OpCode
{
    public NoOp ()
    {
        super(OpCode.NOOP);
    }

    @Override
    public String toString ()
    {
        return OpCode.NOOP;
    }
}