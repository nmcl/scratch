import java.util.*;

public class Verifier extends AmplifierSeries
{
    public static final String[] EXAMPLE_1_COMMANDS = {"3","15","3","16","1002","16","10","16","1","16","15","15","4","15","99","0","0"};
    public static final int MAX_THRUSTER_SIGNAL_1 = 43210;
    public static final String[] EXAMPLE_2_COMMANDS = {"3","23","3","24","1002","24","10","24","1002","23","-1","23","101","5","23","23","1","24","23","23","4","23","99","0","0"};
    public static final int MAX_THRUSTER_SIGNAL_2 = 54321;
    public static final String[] EXAMPLE_3_COMMANDS = {"3","31","3","32","1002","32","10","32","1001","31","-2","31","1007","31","0","33","1002","33","7","33","1","33","31","31","1","32","31","31","4","31","99","0","0","0"};
    public static final int MAX_THRUSTER_SIGNAL_3 = 65210;

    public Verifier (Vector<String> permutations, boolean debug)
    {
        super(permutations, debug);
    }

    public boolean verify ()
    {
        if (verifyInstance(EXAMPLE_1_COMMANDS, MAX_THRUSTER_SIGNAL_1))
        {
            if (verifyInstance(EXAMPLE_2_COMMANDS, MAX_THRUSTER_SIGNAL_2))
            {
                if (verifyInstance(EXAMPLE_3_COMMANDS, MAX_THRUSTER_SIGNAL_3))
                    return true;
                else
                {
                    if (_debug)
                        System.out.println("Verify failed for "+MAX_THRUSTER_SIGNAL_3);
                }
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
        return (maxThrusterSignal(commands) == expected);
    }
}