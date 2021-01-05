public class ParameterMode
{
    /**
     * The Parameter modes.
     * 
     * Parameters that an instruction writes to will never be in immediate mode.
     */

    public static final int POSITION_MODE = 0;  // parameter is interpreted as a position
    public static final int IMMEDIATE_MODE = 1; // parameter is interpreted as a value
}