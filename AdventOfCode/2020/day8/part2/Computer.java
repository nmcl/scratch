import java.util.*;

public class Computer
{
    public Computer (boolean debug)
    {
        _debug = debug;
    }

    public Vector<OpCode> fix (Vector<OpCode> instructions)
    {
        boolean done = false;

        return null;
    }

    public int executeUntilEnd (Vector<OpCode> instructions)
    {
        int index = 0;
        OpCode op = instructions.elementAt(index);
        int accumulator = 0;

        if (_debug)
        {
            System.out.println("\nInstruction: "+index);
            System.out.println("Accumulator: "+accumulator+"\n");
        }

        switch (op.type())
        {
            case OpCode.ACCUMULATOR:
            {
                int value = ((Accumulator) op).getValue();

                if (_debug)
                    System.out.println("acc: adding "+value+" to accumulator "+accumulator);

                accumulator += value;
                index++;
            }
            break;
            case OpCode.JUMP:
            {
                int value = ((Jump) op).getStep();

                if (_debug)
                    System.out.println("jmp: adding "+value+" to index "+index);

                index += ((Jump) op).getStep();
            }
            break;
            case OpCode.NOOP:
            default:
            {
                // no op!!

                if (_debug)
                    System.out.println("nop");

                index++;
            }
            break;
        }

        op.visit();

        op = instructions.elementAt(index);

        return accumulator;
    }

    public int executeUntilInfiniteLoop (Vector<OpCode> instructions)
    {
        int index = 0;
        OpCode op = instructions.elementAt(index);
        int accumulator = 0;

        while (!op.visited())
        {
            if (_debug)
            {
                System.out.println("\nInstruction: "+index);
                System.out.println("Accumulator: "+accumulator+"\n");
            }

            switch (op.type())
            {
                case OpCode.ACCUMULATOR:
                {
                    int value = ((Accumulator) op).getValue();

                    if (_debug)
                        System.out.println("acc: adding "+value+" to accumulator "+accumulator);

                    accumulator += value;
                    index++;
                }
                break;
                case OpCode.JUMP:
                {
                    int value = ((Jump) op).getStep();

                    if (_debug)
                        System.out.println("jmp: adding "+value+" to index "+index);

                    index += ((Jump) op).getStep();
                }
                break;
                case OpCode.NOOP:
                default:
                {
                    // no op!!

                    if (_debug)
                        System.out.println("nop");

                    index++;
                }
                break;
            }

            op.visit();

            op = instructions.elementAt(index);
        }

        return accumulator;
    }

    private boolean _debug;
}