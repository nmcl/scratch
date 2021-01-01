public class Verifier
{
    public static final int[] PHASE_SETTINGS_1 = {4,3,2,1,0};
    public static final String[] EXAMPLE_1_COMMANDS = {"3","15","3","16","1002","16","10","16","1","16","15","15","4","15","99","0","0"};
    public static final int MAX_THRUSTER_SIGNAL_1 = 43210;

    public Verifier (boolean debug)
    {
        _phaseSettings = phaseSettings;
        _debug = debug;

        for (int i = 0; ACS.NUMBER_OF_AMPLIFIERS; i++
            _amps[i] = new Amplifier(_phaseSettings[i], values, debug);
    }

    public boolean verify ()
    {
        
    }

    private Amplifier[] _amps;
    private int[] _phaseSettings;
    private boolean _debug;
}