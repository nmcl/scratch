import java.util.*;

public class GameConsole
{
    public static final String INSTRUCTIONS = "instructions.txt";

    public static void main (String[] args)
    {
        boolean debug = false;
        boolean verify = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-debug] [-verify] [-help]");
                System.exit(0);
            }

            if ("-debug".equals(args[0]))
                debug = true;

            if ("-verify".equals(args[i]))
                verify = true;
        }

        if (verify)
        {
            Verifier v = new Verifier(debug);

            if (v.verify())
                System.out.println("Verified ok.");
            else
                System.out.println("Verify failed!");
        }

        Vector<OpCode> instructions = Util.loadData(INSTRUCTIONS, debug);
        Computer theComputer = new Computer(debug);
        int acc = theComputer.executeUntilInfiniteLoop(instructions);

        System.out.println("Value in the accumulator: "+acc);
    }
}