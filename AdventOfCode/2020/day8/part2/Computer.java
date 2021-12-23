import java.util.*;

public class Computer
{
    public Computer (boolean debug)
    {
        _accumulator = 0;
        _debug = debug;
    }

    public int fixAndExecute (Vector<OpCode> instructions)
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

        return _accumulator;
    }

    public int executeUntilInfiniteLoop (Vector<OpCode> instructions)
    {
        int index = 0;
        OpCode op = instructions.elementAt(index);

        _accumulator = 0;

        while (!op.visited())
        {
            if (_debug)
            {
                System.out.println("\nInstruction: "+index);
                System.out.println("Accumulator: "+_accumulator+"\n");
            }

            switch (op.type())
            {
                case OpCode.ACCUMULATOR:
                {
                    int value = ((Accumulator) op).getValue();

                    if (_debug)
                        System.out.println("acc: adding "+value+" to accumulator "+_accumulator);

                    _accumulator += value;
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

        return _accumulator;
    }

    // either a jmp is supposed to be a nop, or a nop is supposed to be a jmp

    private int changeCode (Vector<OpCode> instructions, Vector<OpCode> copy, int entry)
    {
        Enumeration<OpCode> iter = instructions.elements();
        int entryChanged = 0;
        int index = 0;

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
                        entryChanged = entry +1;
                    }
                    else
                        replacement = new Jump(j.getStep());

                    index++;
                }
                break;
                default:
                {
                    NoOp n = (NoOp) oc;

                    if (index == entry)
                    {
                        replacement = new Jump(n.getValue());
                        entryChanged = entry +1;
                    }
                    else
                        replacement = new NoOp(n.getValue());

                    index++;
                }
                break;
            }

            copy.add(replacement);
        }

        System.out.println("returning "+entryChanged);

        return entryChanged;
    }

    private int _accumulator;
    private boolean _debug;
}