import java.util.*;

public class MovementLogic
{
    public static final int ROUTINE_A = 0;
    public static final int ROUTINE_B = 1;
    public static final int ROUTINE_C = 2;
    public static final int ROUTINE_D = 3;

    public static final String MOVE_LEFT = "L";
    public static final String MOVE_RIGHT = "R";

    public MovementLogic (Map theMap, boolean debug)
    {
        _theMap = theMap;
        _path = new Stack<String>();
        _robotFacing = CellId.ROBOT_FACING_UP;
        _debug = debug;
    }

    public void createMovementFunctions ()
    {

    }

    public void createMovementRoutine ()
    {
        
    }

    /*
     * Create path using only L and R.
     */

    private void createPath ()
    {
        Coordinate currentPosition = new Coordinate(_theMap.findStartingPoint());
        
        if (currentPosition != null)
        {
            // try R then L

        }
        else
            System.out.println("Robot not found!");
    }

    private Coordinate leftPosition (Coordinate coord)
    {
        int x = coord.getX();
        int y = coord.getY();

        switch (_robotFacing)
        {
            case CellId.ROBOT_FACING_DOWN:
            {
                x++;
            }
            break;
            case CellId.ROBOT_FACING_UP:
            {

            }
            case CellId.ROBOT_FACING_LEFT:
            {

            }
            case CellId.ROBOT_FACING_RIGHT:
            default:
        }
    }

    private Map _theMap;
    private Stack<String> _path;
    private String _robotFacing;
    private boolean _debug;
}