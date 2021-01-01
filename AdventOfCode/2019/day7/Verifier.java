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

        //for (int i = 0; i < ACS.NUMBER_OF_AMPLIFIERS; i++)
        //    amps[i] = new Amplifier(PHASE_SETTINGS_1[i], EXAMPLE_1_COMMANDS, _debug);

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

                            amps[0] = new Amplifier(i, EXAMPLE_1_COMMANDS, _debug);
                            amps[1] = new Amplifier(j, EXAMPLE_1_COMMANDS, _debug);
                            amps[2] = new Amplifier(k, EXAMPLE_1_COMMANDS, _debug);
                            amps[3] = new Amplifier(l, EXAMPLE_1_COMMANDS, _debug);
                            amps[4] = new Amplifier(m, EXAMPLE_1_COMMANDS, _debug);

                            // run once with the for-loop values then again with results

                            for (int n = 0; n < ACS.NUMBER_OF_AMPLIFIERS; n++)
                            {
                                results[n] = amps[n].executeCommands();

                                //if (_debug)
                                    System.out.println("Amplifier "+n+" gave initial output: "+results[n]);
                            }

                            for (int n = 0; n < ACS.NUMBER_OF_AMPLIFIERS; n++)
                            {
                                amps[n].resetState();

                                if (n > 0)
                                    amps[n].setPhaseSetting(results[n-1]);
                                else
                                    amps[0].setPhaseSetting(0);
                                
                                results[n] = amps[n].executeCommands();

                                //if (_debug)
                                    System.out.println("Amplifier "+n+" gave final output: "+results[n]);
                            }

                            //if (_debug)
                                System.out.println("Comparing final amplifier output of "+results[ACS.NUMBER_OF_AMPLIFIERS -1]+" with "+maxThrusterSignal);

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