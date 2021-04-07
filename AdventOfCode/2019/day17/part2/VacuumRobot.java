public class VacuumRobot
{
    // instructions

    public static final String LEFT_COMMAND = "L";
    public static final String RIGHT_COMMAND = "R";
    public static final String NEW_LINE = "\n";

    public VacuumRobot (String[] scannedLines, boolean debug)
    {
        _debug = debug;

        convertToPath();
    }

    private void convertToPath ()
    {

    }

    public static final String printCode (String code)
    {
        switch (code)
        {
            case UP_CODE:
                return FACING_UP;
            case DOWN_CODE:
                return FACING_DOWN;
            case RIGHT_CODE:
                return FACING_RIGHT;
            case LEFT_CODE:
                return FACING_LEFT;
            default:
                return FACING_TUMBLING;
        }
    }

    public static final boolean isRobotCode (String code)
    {
        switch (code)
        {
            case UP_CODE:
            case DOWN_CODE:
            case RIGHT_CODE:
            case LEFT_CODE:
            case TUMBLING_CODE:
                return true;
            default:
                return false;
        }
    }

    private boolean _debug;
}