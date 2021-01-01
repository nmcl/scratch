public class Verifier
{
    public static final String[] EXAMPLE_1_COMMANDS = {"3","15","3","16","1002","16","10","16","1","16","15","15","4","15","99","0","0"};
    public static final int MAX_THRUSTER_SIGNAL_1 = 43210;

    public Verifier (boolean debug)
    {
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

        for (int i = 0; i < 5; i++)
        {
            ampID = 0;

            amps[ampID] = new Amplifier(ampID, i, 0, EXAMPLE_1_COMMANDS, _debug);

            results[ampID] = amps[ampID].executeCommands();

            for (int j = 0; j < 5; j++)
            {
                ampID = 1;

                amps[ampID] = new Amplifier(ampID, j, results[ampID-1], EXAMPLE_1_COMMANDS, _debug);

                results[ampID] = amps[ampID].executeCommands();

                for (int k = 0; k < 5; k++)
                {
                    ampID = 2;

                    amps[ampID] = new Amplifier(ampID, j, results[ampID-1], EXAMPLE_1_COMMANDS, _debug);

                    results[ampID] = amps[ampID].executeCommands();

                    for (int l = 0; l < 5; l++)
                    {
                        ampID = 3;

                        amps[ampID] = new Amplifier(ampID, j, results[ampID-1], EXAMPLE_1_COMMANDS, _debug);

                        results[ampID] = amps[ampID].executeCommands();

                        for (int m = 0; m < 5; m++)
                        {
                            ampID = 4;

                            amps[ampID] = new Amplifier(ampID, j, results[ampID-1], EXAMPLE_1_COMMANDS, _debug);

                            results[ampID] = amps[ampID].executeCommands();

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

    private boolean _debug;
}