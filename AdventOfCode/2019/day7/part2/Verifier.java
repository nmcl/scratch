import java.util.*;

public class Verifier extends AmplifierSeries
{
    public static final String[] EXAMPLE_1_COMMANDS = {"3","26","1001","26","-4","26","3","27","1002","27","2","27","1","27","26","27","4","27","1001","28","-1","28","1005","28","6","99","0","0","5"};
    public static final int MAX_THRUSTER_SIGNAL_1 = 139629729;  // 9,8,7,6,5
    public static final String[] EXAMPLE_2_COMMANDS = {"3","52","1001","52","-5","52","3","53","1","52","56","54","1007","54","5","55","1005","55","26","1001","54","-5","54","1105","1","12","1","53","54","53","1008","54","0","55","1001","55","1","55","2","53","55","53","4","53","1001","56","-1","56","1005","56","6","99","0","0","0","0","10"};
    public static final int MAX_THRUSTER_SIGNAL_2 = 18216;  // 9,7,8,5,6

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
        return (maxThrusterSignal(commands) == expected);
    }
}