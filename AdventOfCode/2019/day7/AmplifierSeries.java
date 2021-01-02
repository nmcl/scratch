import java.util.*;

public class AmplifierSeries
{
    public AmplifierSeries (Vector<String> permutations, boolean debug)
    {
        _permutations = permutations;
        _debug = debug;
    }

    protected int maxThrusterSignal (String[] commands)
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

        return maxThrusterSignal;
    }

    protected Vector<String> _permutations;
    protected boolean _debug;
}