public class OpCode
{
    public static final String ACCUMULATOR = "acc";
    public static final String JUMP = "jmp";
    public static final String NOP = "nop";

    protected OpCode (String type)
    {
        _type = type;
    }

    protected String _type;
}