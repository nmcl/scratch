public class OpCode
{
    public static final String ACCUMULATOR = "acc";
    public static final String JUMP = "jmp";
    public static final String NOOP = "nop";

    protected OpCode (String type)
    {
        _type = type;
        _executed = false;
    }

    public void visit ()
    {
        _executed = true;
    }

    public boolean visited ()
    {
        return _executed;
    }

    protected String _type;
    protected boolean _executed;
}