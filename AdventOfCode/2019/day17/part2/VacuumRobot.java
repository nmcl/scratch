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
            case CellId.ROBOT_UP_CODE:
                return CellId.ROBOT_FACING_UP;
            case CellId.ROBOT_DOWN_CODE:
                return CellId.ROBOT_FACING_DOWN;
            case CellId.ROBOT_RIGHT_CODE:
                return CellId.ROBOT_FACING_RIGHT;
            case CellId.ROBOT_LEFT_CODE:
                return CellId.ROBOT_FACING_LEFT;
            default:
                return CellId.ROBOT_FACING_TUMBLING;
        }
    }

    public static final boolean isRobotCode (String code)
    {
        switch (code)
        {
            case CellId.ROBOT_UP_CODE:
            case CellId.ROBOT_DOWN_CODE:
            case CellId.ROBOT_RIGHT_CODE:
            case CellId.ROBOT_LEFT_CODE:
            case CellId.ROBOT_TUMBLING_CODE:
                return true;
            default:
                return false;
        }
    }

    private boolean _debug;
}