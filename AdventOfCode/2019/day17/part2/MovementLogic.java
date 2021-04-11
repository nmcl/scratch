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
        _currentPosition = new Coordinate(_theMap.findStartingPoint());
        _debug = debug;
    }

    public void createMovementFunctions ()
    {
        createPath();

        String pathElement = _path.pop();

        System.out.println("Path:");

        while (pathElement != null)
        {
            System.out.println(pathElement);

            try
            {
                _path.pop();
            }
            catch (Exception ex)
            {
                pathElement = null;
            }
        }
    }

    public void createMovementRoutine ()
    {
        
    }

    /*
     * Create path using only L and R.
     */

    private void createPath ()
    {
        if (_currentPosition != null)
        {
            /*
             * Try R then L.
             * 
             * Robot always starts facing up. Don't change facing until
             * we run into a "wall" (open space, i.e., scaffolding).
             */

            if (!tryMoveRight())
                tryMoveLeft();
        }
        else
            System.out.println("Robot not found!");
    }

    private boolean tryMoveRight ()
    {
        Coordinate coord = rightPosition(_currentPosition);

        if (_theMap.isScaffold(coord))
        {
            _path.push(MOVE_RIGHT);
            _currentPosition = coord;

            return tryMoveRight();
        }
        else
            return false;
    }

    private boolean tryMoveLeft ()
    {
        Coordinate coord = leftPosition(_currentPosition);

        if (_theMap.isScaffold(coord))
        {
            _path.push(MOVE_LEFT);
            _currentPosition = coord;

            return tryMoveLeft();
        }
        else
            return false;
    }

    private final Coordinate rightPosition (Coordinate coord)
    {
        int x = coord.getX();
        int y = coord.getY();

        switch (_robotFacing)
        {
            case CellId.ROBOT_FACING_DOWN:
            {
                x--;
            }
            break;
            case CellId.ROBOT_FACING_UP:
            {
                x++;
            }
            break;
            case CellId.ROBOT_FACING_LEFT:
            {
                y++;
            }
            break;
            case CellId.ROBOT_FACING_RIGHT:
            default:
            {
                y--;
            }
            break;
        }

        return new Coordinate(x, y);
    }

    private final Coordinate leftPosition (Coordinate coord)
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
                x--;
            }
            break;
            case CellId.ROBOT_FACING_LEFT:
            {
                y--;
            }
            break;
            case CellId.ROBOT_FACING_RIGHT:
            default:
            {
                y++;
            }
            break;
        }

        return new Coordinate(x, y);
    }

    private Map _theMap;
    private Stack<String> _path;
    private String _robotFacing;
    private Coordinate _currentPosition;
    private boolean _debug;
}