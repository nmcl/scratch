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

        /*
         * Not the most efficient as we keep creating new Amplifier instances
         * at each iteration. Once this works, replace with just resetting an
         * existing Amplifier instance if it exists.
         */

        // Each phase setting is used exactly once.

        for (int i = 0; i < 5; i++)
        {
            ampID = 0;

            amps[ampID] = new Amplifier(ampID, i, 0, EXAMPLE_1_COMMANDS, _debug);

            results[ampID] = amps[ampID].executeCommands();

            System.out.println("Amplifier "+ampID+"  returned "+results[ampID]);

            for (int j = 0; j < 5; j++)
            {
                if (j == i)
                    break;

                ampID = 1;

                amps[ampID] = new Amplifier(ampID, j, results[ampID-1], EXAMPLE_1_COMMANDS, _debug);

                results[ampID] = amps[ampID].executeCommands();

                System.out.println("Amplifier "+ampID+"  returned "+results[ampID]);

                for (int k = 0; k < 5; k++)
                {
                    if ((k == j) || (k == i))
                        break;

                    ampID = 2;

                    amps[ampID] = new Amplifier(ampID, k, results[ampID-1], EXAMPLE_1_COMMANDS, _debug);

                    results[ampID] = amps[ampID].executeCommands();

                    System.out.println("Amplifier "+ampID+"  returned "+results[ampID]);

                    for (int l = 0; l < 5; l++)
                    {
                        if ((l == k) || (l == j) || (l == i))
                            break;

                        ampID = 3;

                        amps[ampID] = new Amplifier(ampID, l, results[ampID-1], EXAMPLE_1_COMMANDS, _debug);

                        results[ampID] = amps[ampID].executeCommands();

                        System.out.println("Amplifier "+ampID+"  returned "+results[ampID]);

                        for (int m = 0; m < 5; m++)
                        {
                            if ((m == l) || (m == k) || (m == j) || (m == i))
                                break;
                                
                            ampID = 4;

                            amps[ampID] = new Amplifier(ampID, m, results[ampID-1], EXAMPLE_1_COMMANDS, _debug);

                            results[ampID] = amps[ampID].executeCommands();

                            System.out.println("Amplifier "+ampID+"  returned "+results[ampID]);
                            
                            System.out.println("**indexes "+i+" "+j+" "+k+" "+l+" "+m);
                            
                            for (int z = 0; z < 5; z++)
                                System.out.println(amps[z]);

                            if (results[ampID] > maxThrusterSignal)
                                maxThrusterSignal = results[ampID];
                        }
                    }
                }
            }
        }

        System.out.println("Max thrusters: "+maxThrusterSignal);

        return (maxThrusterSignal == MAX_THRUSTER_SIGNAL_1);
    }

    private Vector<String> _permutations;
    private boolean _debug;
}