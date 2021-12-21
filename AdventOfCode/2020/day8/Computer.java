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
            if (_debug)
                System.out.println("Executing: "+op);

            switch (op.type())
            {
                case OpCode.ACCUMULATOR:
                {
                    int value = ((Accumulator) op).getValue();

                    if (_debug)
                        System.out.println("Adding "+value+" to accumulator "+_accumulator);

                    _accumulator += value;
                }
                break;
                case OpCode.JUMP:
                {
                    int value = ((Jump) op).getStep();

                    if (_debug)
                        System.out.println("Adding "+value+" to index "+index);

                    index += ((Jump) op).getStep();
                }
                break;
                case OpCode.NOOP:
                default:
                {
                    // no op!!
                }
                break;
            }

            index++;

            op.visit();

            op = instructions.elementAt(index);
        }

        return _accumulator;
    }

    private int _accumulator;
    private boolean _debug;
}