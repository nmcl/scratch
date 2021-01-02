import java.util.*;

public class Verifier
{
    public static final String[] EXAMPLE_1_COMMANDS = {"3","15","3","16","1002","16","10","16","1","16","15","15","4","15","99","0","0"};
    public static final int MAX_THRUSTER_SIGNAL_1 = 43210;

    public Verifier (Vector<String> permutations, boolean debug)
    {
        _permutations = permutations;
        _debug = debug;
    }

    public boolean verify ()
    {
        Amplifier[] amps = new Amplifier[ACS.NUMBER_OF_AMPLIFIERS];
        int maxThrusterSignal = 0;
        int[] results = new int[ACS.NUMBER_OF_AMPLIFIERS];
        int ampID = 0;
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

                System.out.println("**Phase setting "+i+" is "+phaseSetting[i]);
            }

            amps[0] = new Amplifier(0, phaseSetting[0], 0, EXAMPLE_1_COMMANDS, _debug);

            results[0] = amps[0].executeCommands();

            System.out.println("Amplifier 0 returned "+results[0]);

            amps[1] = new Amplifier(1, phaseSetting[1], results[0], EXAMPLE_1_COMMANDS, _debug);

            results[1] = amps[1].executeCommands();

            System.out.println("Amplifier 1 returned "+results[1]);

            amps[2] = new Amplifier(2, phaseSetting[2], results[1], EXAMPLE_1_COMMANDS, _debug);

            results[2] = amps[2].executeCommands();

            System.out.println("Amplifier 2 returned "+results[2]);

            amps[3] = new Amplifier(3, phaseSetting[3], results[2], EXAMPLE_1_COMMANDS, _debug);

            results[3] = amps[3].executeCommands();

            System.out.println("Amplifier 3 returned "+results[3]);

            amps[4] = new Amplifier(4, phaseSetting[4], results[3], EXAMPLE_1_COMMANDS, _debug);

            results[4] = amps[4].executeCommands();

            System.out.println("Amplifier 4 returned "+results[4]);

            if (results[4] > maxThrusterSignal)
                maxThrusterSignal = results[4];
        }

        System.out.println("Max thrusters: "+maxThrusterSignal);

        return (maxThrusterSignal == MAX_THRUSTER_SIGNAL_1);
    }

    private Vector<String> _permutations;
    private boolean _debug;
}