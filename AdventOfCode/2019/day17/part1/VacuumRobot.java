public class VacuumRobot
{
    public static final String UP = "^";
    public static final String DOWN = "v";
    public static final String LEFT = "<";
    public static final String RIGHT = ">";
    public static final String TUMBLING = "X";

    // ASCII codes.

    public static final String UP_CODE = "94";
    public static final String DOWN_CODE = "118";
    public static final String RIGHT_CODE = "60";
    public static final String LEFT_CODE = "62";
    public static final String TUMBLING_CODE = "88";

    public VacuumRobot (boolean debug)
    {
        _debug = debug;
    }

    public static final String print (String code)
    {
        switch (code)
        {
            case UP_CODE:
                return UP;
            case DOWN_CODE:
                return DOWN;
            case RIGHT_CODE:
                return RIGHT;
            case LEFT_CODE:
                return LEFT;
            default:
                return TUMBLING;
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