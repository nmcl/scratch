import java.util.*;

public class Verifier
{
    public static final String[] EXAMPLE_1_COMMANDS = {"3","15","3","16","1002","16","10","16","1","16","15","15","4","15","99","0","0"};
    public static final int MAX_THRUSTER_SIGNAL_1 = 43210;
    public static final String[] EXAMPLE_2_COMMANDS = {"3","23","3","24","1002","24","10","24","1002","23","-1","23","101","5","23","23","1","24","23","23","4","23","99","0","0"};
    public static final int MAX_THRUSTER_SIGNAL_2 = 54321;

    public Verifier (Vector<String> permutations, boolean debug)
    {
        _permutations = permutations;
        _debug = debug;
    }

    public boolean verify ()
    {
        if (verifyInstance(EXAMPLE_1_COMMANDS, MAX_THRUSTER_SIGNAL_1))
        {
            if (verifyInstance(EXAMPLE_2_COMMANDS, MAX_THRUSTER_SIGNAL_2))
                return true;
            else
            {
                if (_debug)
                    System.out.println("Verify failed for "+EXAMPLE_2_COMMANDS);
            }
        }
        else
        {
            if (_debug)
                System.out.println("Verify failed for "+EXAMPLE_1_COMMANDS);
        }

        return false;
    }

    private boolean verifyInstance (String[] commands, int expected)
    {
        Amplifier[] amps = new Amplifier[ACS.NUMBER_OF_AMPLIFIERS];
        int maxThrusterSignal = 0;
        int[] results = new int[ACS.NUMBER_OF_AMPLIFIERS];
        Enumeration<String> iter = _permutations.elements();

        /*
         * Not the most efficient as we keep creating new Amplifier instances
         * at each iteration. Once this works, replace with just resetting an
         * existing Amplifier instance if it exists.
         */

        while (iter.hasMoreElements())
        {
            String permutation = iter.nextElement();
            int[] phaseSetting = new int[permutation.length()];

            for (int i = 0; i < permutation.length(); i++)
            {
                phaseSetting[i] = Character.getNumericValue(permutation.charAt(i));

                if (_debug)
                    System.out.println("Phase setting "+i+" is "+phaseSetting[i]);
            }

            amps[0] = new Amplifier(0, phaseSetting[0], 0, commands, _debug);

            results[0] = amps[0].executeCommands();

            if (_debug)
                System.out.println("Amplifier 0 returned "+results[0]);

            amps[1] = new Amplifier(1, phaseSetting[1], results[0], commands, _debug);

            results[1] = amps[1].executeCommands();

            if (_debug)
                System.out.println("Amplifier 1 returned "+results[1]);

            amps[2] = new Amplifier(2, phaseSetting[2], results[1], commands, _debug);

            results[2] = amps[2].executeCommands();

            if (_debug)
                System.out.println("Amplifier 2 returned "+results[2]);

            amps[3] = new Amplifier(3, phaseSetting[3], results[2], commands, _debug);

            results[3] = amps[3].executeCommands();

            if (_debug)
                System.out.println("Amplifier 3 returned "+results[3]);

            amps[4] = new Amplifier(4, phaseSetting[4], results[3], commands, _debug);

            results[4] = amps[4].executeCommands();

            if (_debug)
                System.out.println("Amplifier 4 returned "+results[4]);

            if (results[4] > maxThrusterSignal)
                maxThrusterSignal = results[4];
        }

        if (_debug)
            System.out.println("Max thrusters: "+maxThrusterSignal);

        return (maxThrusterSignal == expected);
    }

    private Vector<String> _permutations;
    private boolean _debug;
}