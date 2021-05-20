/**
 * The Camera Image or representations of the "maze" are made
 * up of Cells and the values are maintained by this class.
 */

public class CellId
{
    // Robot facing characters
    
    public static final String ROBOT_FACING_UP = "^";
    public static final String ROBOT_FACING_DOWN = "v";
    public static final String ROBOT_FACING_LEFT = "<";
    public static final String ROBOT_FACING_RIGHT = ">";
    public static final String ROBOT_FACING_TUMBLING = "X";

    // ASCII codes.

    public static final String ROBOT_UP_CODE = "94";
    public static final String ROBOT_DOWN_CODE = "118";
    public static final String ROBOT_RIGHT_CODE = "60";
    public static final String ROBOT_LEFT_CODE = "62";
    public static final String ROBOT_TUMBLING_CODE = "88";

    public static final String SCAFFOLDING = "#";
    public static final String OPEN_SPACE = ".";

    public static final char SCAFFOLDING_CHAR = '#';
    public static final char INTERSECTION_CHAR = 'O';

    // ASCII codes.

    public static final String SCAFFOLDING_CODE = "35";
    public static final String OPEN_SPACE_CODE = "46";
    public static final String NEW_LINE_CODE = "10";

    // Robot movement command.

    public static final String MOVE_LEFT = "L";
    public static final String MOVE_RIGHT = "R";

    public static final String printRobotCode (String code)
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
    
    private CellId ()
    {
    }
}