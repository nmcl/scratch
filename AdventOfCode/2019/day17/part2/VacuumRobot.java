public class VacuumRobot
{
    public static final String FACING_UP = "^";
    public static final String FACING_DOWN = "v";
    public static final String FACING_RIGHT = "<";
    public static final String FACING_LEFT = ">";
    public static final String FACING_TUMBLING = "X";

    // ASCII codes.

    public static final String UP_CODE = "94";
    public static final String DOWN_CODE = "118";
    public static final String RIGHT_CODE = "60";
    public static final String LEFT_CODE = "62";
    public static final String TUMBLING_CODE = "88";

    // instructions

    public static final String LEFT_COMMAND = "L";
    public static final String RIGHT_COMMAND = "R";
    public static final String NEW_LINE = "\n";

    public VacuumRobot (String[] scannedLines, boolean debug)
    {
        _debug = debug;
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