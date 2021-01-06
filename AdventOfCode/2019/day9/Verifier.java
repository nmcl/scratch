import java.util.*;

public class Verifier
{
    public static final String[] EXAMPLE_1_COMMANDS = {"109","1","204","-1","1001","100","1","100","1008","100","16","101","1006","101","0","99"};
    public static final String[] EXAMPLE_1_RESULTS = {"109","1","204","-1","1001","100","1","100","1008","100","16","101","1006","101","0","99"};
    public static final String[] EXAMPLE_2_COMMANDS = {"1102","34915192","34915192","7","4","7","99","0"};
    public static final String[] EXAMPLE_2_RESULTS = {""};
    public static final String[] EXAMPLE_3_COMMANDS = {"104","1125899906842624","99"};
    public static final String[] EXAMPLE_3_RESULTS = {"1125899906842624"};

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Intcode theComputer = new Intcode(EXAMPLE_1_COMMANDS, _debug);
        Vector<String> results = new Vector<String>();
        
        while (!theComputer.hasHalted())
            results = theComputer.executeProgram(0, 0);

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

        return verified;
    }

    private boolean _debug;
}