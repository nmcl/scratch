public class Verifier
{
    public static final int[] PHASE_SETTINGS_1 = {4,3,2,1,0};
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

        for (int i = 0; i < ACS.NUMBER_OF_AMPLIFIERS; i++)
            amps[i] = new Amplifier(PHASE_SETTINGS_1[i], EXAMPLE_1_COMMANDS, _debug);

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
                            
                        }
                    }
                }
            }
        }
    }

    private boolean _debug;
}