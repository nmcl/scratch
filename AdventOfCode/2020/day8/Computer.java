import java.util.*;

public class Computer
{
    public Computer (boolean debug)
    {
        _accumulator = 0;
        _debug = debug;
    }

    public int executeUntilInfiniteLoop (Vector<OpCode> instructions)
    {
        int index = 0;
        OpCode op = instructions.elementAt(index);

        _accumulator = 0;

        while (!op.visited())
        {
            switch (op.type())
            {
                case OpCode.ACCUMULATOR:
                {
                    _accumulator += ((Accumulator) op).getValue();
                }
                break;
                case OpCode.JUMP:
                {

                }
                break;
                case OpCode.NOOP:
                default:
                {

                }
                break;
            }
        }

        return -1;
    }

    private int _accumulator;
    private boolean _debug;
}