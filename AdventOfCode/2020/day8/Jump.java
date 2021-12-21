// jumps to a new instruction relative to itself

public class Jump extends OpCode
{
    public Jump (int step)
    {
        super(OpCode.JUMP);

        _step = step;
    }

    private int _step;
}