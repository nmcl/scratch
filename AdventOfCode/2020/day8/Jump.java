// jumps to a new instruction relative to itself

public class Jump extends OpCode
{
    public Jump (int step)
    {
        super(OpCode.JUMP);

        _step = step;
    }

    public int getStep ()
    {
        return _step;
    }

    @Override
    public String toString ()
    {
        return OpCode.JUMP+" "+_step;
    }

    private int _step;
}