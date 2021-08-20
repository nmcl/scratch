import java.util.*;

public class SpringDroid
{
    public static final int MAX_INSTRUCTIONS = 15;

    public static final String INSTRUCTIONS = "instructions.txt";

    /*
     * We know that after the droid jumps it will land 4 tiles away.
     * Only safe to jump when D is solid or we go spinning into space!
     * Only jump when there's a hole in front of the droid, i.e., A, B or C is
     * a space.
     *
     * Jump if there is a hole on A or on B or on C and there is no hole on D.
     */

    public static void main (String[] args)
    {
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-debug] [-help]");

                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;
        }

        Vector<String> values = IntcodeUtil.readValues(INSTRUCTIONS);
        Intcode computer = new Intcode(values, debug);
        List<String> program = List.of( // jump any space if D is safe
            "NOT A J\n",
            "NOT B T\n",
            "OR T J\n",
            "NOT C T\n",
            "OR T J\n",
            "AND D J\n",
            "WALK\n"
        );

        Iterator<String> iter = program.iterator();

        while (iter.hasNext())
        {
            String str = iter.next();

            computer.setInputs(IntcodeUtil.convertStringToASCIIArray(str));
        }

        computer.executeProgram();

        Vector<String> outputs = computer.getOutputs();

        System.out.println("Hull damage: "+outputs.elementAt(outputs.size() -1));
    }
}