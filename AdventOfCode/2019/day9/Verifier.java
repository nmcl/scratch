import java.util.*;

public class Verifier
{
    public static final String[] EXAMPLE_1_COMMANDS = {"109","1","204","-1","1001","100","1","100","1008","100","16","101","1006","101","0","99"};
    public static final String[] EXAMPLE_1_RESULTS = {"109","1","204","-1","1001","100","1","100","1008","100","16","101","1006","101","0","99"};
    public static final String[] EXAMPLE_2_COMMANDS = {"1102","34915192","34915192","7","4","7","99","0"};
    public static final String EXAMPLE_2_RESULTS = "1219070632396864";
    public static final String[] EXAMPLE_3_COMMANDS = {"104","1125899906842624","99"};
    public static final String EXAMPLE_3_RESULTS = "1125899906842624";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<String> instructions = new Vector<String>();
        Vector<String> results = new Vector<String>();

        instructions.addAll(Arrays.asList(EXAMPLE_1_COMMANDS));

        Intcode theComputer = new Intcode(instructions, 0, _debug);
        
        while (!theComputer.hasHalted())
            results = theComputer.executeProgram();

        Enumeration<String> iter = results.elements();
        boolean verified = true;
        int index = 0;

        while (iter.hasMoreElements() && verified)
        {
            String item = iter.nextElement();

            if (!EXAMPLE_1_RESULTS[index].equals(item))
                verified = false;
            else
                index++;
        }

        if (verified)
        {
            instructions = new Vector<String>();
            results = new Vector<String>();

            instructions.addAll(Arrays.asList(EXAMPLE_2_COMMANDS));

            theComputer = new Intcode(instructions, 0, _debug);
            results = theComputer.executeProgram();

            verified = false;

            if (EXAMPLE_2_RESULTS.equals(results.elementAt(0)))
            {
                instructions = new Vector<String>();
                results = new Vector<String>();

                instructions.addAll(Arrays.asList(EXAMPLE_3_COMMANDS));

                theComputer = new Intcode(instructions, 0, _debug);
                results = theComputer.executeProgram();

                if (EXAMPLE_3_RESULTS.equals(results.elementAt(0)))
                    verified = true;
                else
                    System.out.println("Verify failed for EXAMPLE_3_COMMANDS");
            }
            else
                System.out.println("Verify failed for EXAMPLE_2_COMMANDS");
        }
        else
            System.out.println("Verify failed for EXAMPLE_1_COMMANDS");

        return verified;
    }

    private boolean _debug;
}