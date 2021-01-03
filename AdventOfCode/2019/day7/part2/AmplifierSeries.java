import java.util.*;

public class AmplifierSeries
{
    public AmplifierSeries (Vector<String> permutations, boolean debug)
    {
        _permutations = permutations;
        _debug = debug;
        _amps = null;
        _initialProgram = null;
    }

    public void setProgram (String[] initialProgram)
    {
        _initialProgram = new String[initialProgram.length];

        System.arraycopy(initialProgram, 0, _initialProgram, 0, initialProgram.length);
    }

    public int maxThrusterSignal ()
    {
        int maxThrusterSignal = 0;
        Enumeration<String> iter = _permutations.elements();

        while (iter.hasMoreElements())
        {
            String permutation = iter.nextElement();
            int[] phaseSetting = new int[permutation.length()];
            int loop = 0;
            boolean halted = false;
            int[] results = new int[ACS.NUMBER_OF_AMPLIFIERS];

            initialiseAmplifiers();  // do this at the start of each permutation.

            for (int i = 0; i < permutation.length(); i++)
            {
                phaseSetting[i] = Character.getNumericValue(permutation.charAt(i));

                if (_debug)
                    System.out.println("Phase setting "+i+" is "+phaseSetting[i]);
            }

            while (!halted)
            {
                if (!_amps[0].halted())
                {
                    if (loop == 0)
                        results[0] = _amps[0].executeProgram(phaseSetting[0], 0);
                    else
                        results[0] = _amps[0].executeProgram(phaseSetting[0], results[4]);

                    loop++;

                    if (_debug)
                        System.out.println("Amplifier 0 returned "+results[0]);
                }
                else
                {
                    if (_debug)
                        System.out.println("Amplifier 0 has already halted.");
                }

                if (!_amps[1].halted())
                {
                    results[1] = _amps[1].executeProgram(phaseSetting[1], results[0]);

                    if (_debug)
                        System.out.println("Amplifier 1 returned "+results[1]);
                }
                else
                {
                    if (_debug)
                        System.out.println("Amplifier 1 has already halted.");
                }

                if (!_amps[2].halted())
                {
                    results[2] = _amps[2].executeProgram(phaseSetting[2], results[1]);

                    if (_debug)
                        System.out.println("Amplifier 2 returned "+results[2]);
                }
                else
                {
                    if (_debug)
                        System.out.println("Amplifier 2 has already halted.");
                }

                if (!_amps[3].halted())
                {
                    results[3] = _amps[3].executeProgram(phaseSetting[3], results[2]);

                    if (_debug)
                        System.out.println("Amplifier 3 returned "+results[3]);
                }
                else
                {
                    if (_debug)
                        System.out.println("Amplifier 3 has already halted.");
                }

                if (!_amps[4].halted())
                {
                    results[4] = _amps[4].executeProgram(phaseSetting[4], results[3]);

                    if (_debug)
                        System.out.println("Amplifier 4 returned "+results[4]);

                    if (_amps[4].halted())
                        halted = true;
                }
                else
                {
                    if (_debug)
                        System.out.println("Amplifier 4 has already halted.");
                }

                // it's the last output from amp 4 we want, not every output.

                if (_amps[4].halted() && (results[4] > maxThrusterSignal))
                    maxThrusterSignal = results[4];
            }
        }

        if (_debug)
            System.out.println("Max thrusters: "+maxThrusterSignal);

        return maxThrusterSignal;
    }

    private void initialiseAmplifiers ()
    {
        _amps = new Amplifier[ACS.NUMBER_OF_AMPLIFIERS];

        for (int i = 0; i < ACS.NUMBER_OF_AMPLIFIERS; i++)
        {
            _amps[i] = new Amplifier(i, _initialProgram, _debug);
        }
    }

    protected Vector<String> _permutations;
    protected boolean _debug;
    private Amplifier[] _amps = null;
    String[] _initialProgram;
}