public class ParameterMode
{
    /**
     * The Parameter modes.
     * 
     * Parameters that an instruction writes to will never be in immediate mode.
     */

    public static final int POSITION_MODE = 0;  // parameter is interpreted as a position
    public static final int IMMEDIATE_MODE = 1; // parameter is interpreted as a value
    public static final int RELATIVE_MODE = 2;  // the parameter is interpreted as a position relative to base

    private static final int MAX_PARAMETERS = 3;

    /*
     * Return the modes for the parameters, including default mode
     * (POSITION_MODE) if nothing is defined.
     */

    public static final int[] getModes (String digits)
    {
        int[] theModes = new int[MAX_PARAMETERS];

        for (int i = 0; i < MAX_PARAMETERS; i++)
            theModes[i] = ParameterMode.POSITION_MODE;

        if ((digits != null) && (digits.length() > 2))
        {
            String allModes = digits.substring(0, digits.length()-2);
            char[] modeArray = allModes.toCharArray();

            for (int j = modeArray.length-1; j >= 0; j--)
            {
                if (modeArray[j] == '1')
                    theModes[modeArray.length-j-1] = ParameterMode.IMMEDIATE_MODE;
            }
        }

        return theModes;
    }

    public static final void printModes (int[] modes)
    {
        for (int i = 0; i < modes.length; i++)
        {
            System.out.print("Parameter "+i+" is ");
            
            if (modes[i] == ParameterMode.IMMEDIATE_MODE)
                System.out.println("immediate mode");
            else
            {
                if (modes[i] == ParameterMode.POSITION_MODE)
                    System.out.println("position mode");
                else
                {
                    if (modes[i] == ParameterMode.RELATIVE_MODE)
                        System.out.println("relative mode");
                    else
                        System.out.println("unknown mode!");
                }
            }
        }
    }
}