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
        super(debug);
    }

    public boolean verify ()
    {
        if (verifyInstance(EXAMPLE_1_COMMANDS, MAX_THRUSTER_SIGNAL_1))
        {
            if (verifyInstance(EXAMPLE_2_COMMANDS, MAX_THRUSTER_SIGNAL_2))
            {
                return true;
            }
            else
            {
                if (_debug)
                    System.out.println("Verify failed for "+MAX_THRUSTER_SIGNAL_2);
            }
        }
        else
        {
            if (_debug)
                System.out.println("Verify failed for "+MAX_THRUSTER_SIGNAL_1);
        }

        return false;
    }

    private boolean verifyInstance (String[] commands, int expected)
    {
        setProgram(commands);

        return (maxThrusterSignal() == expected);
    }
}