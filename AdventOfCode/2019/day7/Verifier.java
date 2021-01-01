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

        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                for (int k = 0; k < 5; k++)
                {
                    for (int l = 0; l < 5; l++)
                    {
                        for (int m = 0; m < 5; m++)
                        {
                            int[] results = new int[ACS.NUMBER_OF_AMPLIFIERS];

                            for (int ampID = 0; ampID < ACS.NUMBER_OF_AMPLIFIERS; ampID++)
                            {
                                if (ampID == 0)
                                {
                                    amps[0] = new Amplifier(ampID, i, 0, EXAMPLE_1_COMMANDS, _debug);

                                    System.out.println("Created amplifier "+amps[0]);
                                }
                                else
                                    amps[ampID] = new Amplifier(ampID, i, results[ampID-1], EXAMPLE_1_COMMANDS, _debug);

                                results[ampID] = amps[ampID].executeCommands();

                                System.out.println("Amplifier "+ampID+" returned "+results[ampID]);
                            }

                            System.out.println("Comparing "+maxThrusterSignal+" and "+results[ACS.NUMBER_OF_AMPLIFIERS -1]);

                            if (results[ACS.NUMBER_OF_AMPLIFIERS -1] > maxThrusterSignal)
                                maxThrusterSignal = results[ACS.NUMBER_OF_AMPLIFIERS -1];
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