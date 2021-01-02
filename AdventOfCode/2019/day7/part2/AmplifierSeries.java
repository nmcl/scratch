import java.util.*;

public class AmplifierSeries
{
    public AmplifierSeries (Vector<String> permutations, boolean debug)
    {
        _permutations = permutations;
        _debug = debug;

        for (int i = 0; i < ACS.NUMBER_OF_AMPLIFIERS; i++)
            _amps[i] = new Amplifier(i, _debug);
    }

    protected int maxThrusterSignal (String[] commands)
    {
        int maxThrusterSignal = 0;
        int[] results = new int[ACS.NUMBER_OF_AMPLIFIERS];
        Enumeration<String> iter = _permutations.elements();

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

            _amps[0].changeInputCodes(phaseSetting[0], 0);
            _amps[0].changeCommands(commands);

            results[0] = _amps[0].executeCommands();

            if (_debug)
                System.out.println("Amplifier 0 returned "+results[0]);

            _amps[1].changeInputCodes(phaseSetting[1], results[0]);
            _amps[1].changeCommands(commands);

            results[1] = _amps[1].executeCommands();

            if (_debug)
                System.out.println("Amplifier 1 returned "+results[1]);

            _amps[2].changeInputCodes(phaseSetting[2], results[1]);
            _amps[2].changeCommands(commands);

            results[2] = _amps[2].executeCommands();

            if (_debug)
                System.out.println("Amplifier 2 returned "+results[2]);

            _amps[3].changeInputCodes(phaseSetting[3], results[2]);
            _amps[3].changeCommands(commands);

            results[3] = _amps[3].executeCommands();

            if (_debug)
                System.out.println("Amplifier 3 returned "+results[3]);

            _amps[4].changeInputCodes(phaseSetting[4], results[3]);
            _amps[4].changeCommands(commands);

            results[4] = _amps[4].executeCommands();

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
    private Amplifier[] _amps = new Amplifier[ACS.NUMBER_OF_AMPLIFIERS];
}