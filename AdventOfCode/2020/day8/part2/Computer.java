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
        int index = 0;

        while (!done)
        {
            try
            {
                Vector<OpCode> attempt = new Vector<OpCode>();
                
                index = changeCode(instructions, attempt, index);

                if (_debug)
                    Util.printInstructions(attempt);

                executeUntilInfiniteLoop(attempt);

                System.out.println("**infinite loop");
            }
            catch (ArrayIndexOutOfBoundsException ex)
            {
                // if we fix the program then we'll drop off the end of the program

                ex.printStackTrace();

                done = true;
            }
        }

        return null;
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

    // either a jmp is supposed to be a nop, or a nop is supposed to be a jmp

    private int changeCode (Vector<OpCode> instructions, Vector<OpCode> copy, int entry)
    {
        int index = 0;
        Enumeration<OpCode> iter = instructions.elements();

        System.out.println("**changing "+entry);

        while (iter.hasMoreElements())
        {
            OpCode oc = iter.nextElement();
            OpCode replacement = null;

            switch (oc.type())
            {
                case OpCode.ACCUMULATOR:
                {
                    Accumulator a = (Accumulator) oc;

                    replacement = new Accumulator(a.getValue());
                }
                break;
                case OpCode.JUMP:
                {
                    Jump j = (Jump) oc;

                    if (index == entry)
                    {
                        replacement = new NoOp(j.getStep());

                        index++;
                    }
                }
                break;
                default:
                {
                    NoOp n = (NoOp) oc;

                    if (index == entry)
                    {
                        replacement = new Jump(n.getValue());

                        index++;
                    }
                }
                break;
            }

            copy.add(replacement);
        }

        System.out.println("returning "+index);

        return index;
    }

    private boolean _debug;
}