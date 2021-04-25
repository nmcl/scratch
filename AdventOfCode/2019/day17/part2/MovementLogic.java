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
        _robotTrack = new Trail(_theMap);
        _path = new Stack<String>();
        _robotFacing = CellId.ROBOT_FACING_UP;
        _currentMoveDirection = "";
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

    private boolean createPath ()
    {
        System.out.println("createPath from: "+_currentPosition);

        if (_currentPosition != null)
        {
            /*
             * Try L then R.
             * 
             * Robot always starts facing up. Change facing when we
             * start to move but remember initial facing so we can
             * refer movement directions as L or R.
             */

            if (!tryToMove(MOVE_LEFT, leftCoordinate(_currentPosition)))
            {
                return tryToMove(MOVE_RIGHT, rightCoordinate(_currentPosition));
            }
            else
                return true;
        }
        else
        {
            System.out.println("Robot not found!");

            return false;
        }
    }

    private boolean tryToMove (String direction, Coordinate coord)
    {
        System.out.println("tryMove: "+coord+" with direction: "+direction);

        if (_robotTrack.visited(coord))
        {
            System.out.println("Robot already visited this location.");

            return false;
        }

        if (_theMap.isScaffold(coord))
        {
            System.out.println("Is scaffolding!");
            
            _currentMoveDirection = direction;
            _path.push(_currentMoveDirection);
            
            _robotTrack.changeElement(coord, _currentMoveDirection);
            _currentPosition = coord;

            System.out.println("\n"+_robotTrack);

            return createPath();
        }
        else
        {
            System.out.println("Robot was facing "+_robotFacing+" and moving "+direction);

            changeFacing();

            System.out.println("Robot now facing "+_robotFacing);

            return tryToMove(getNextDirection(), coord);
        }
    }

    private String getNextDirection ()
    {
        Coordinate coord = leftCoordinate(_currentPosition);

        if (_robotTrack.visited(coord))
        {
            coord = rightCoordinate(_currentPosition);

            if (_robotTrack.visited(coord))
                return null;
            else
                return MOVE_RIGHT;
        }
        else
            return MOVE_LEFT;
    }

    private void changeFacing ()
    {
        switch (_robotFacing)
        {
            case CellId.ROBOT_FACING_UP:
            {
                if (_currentMoveDirection.equals(MOVE_LEFT))
                    _robotFacing = CellId.ROBOT_FACING_LEFT;
                else
                    _robotFacing = CellId.ROBOT_FACING_RIGHT;
            }
            break;
            case CellId.ROBOT_FACING_DOWN:
            {
                if (_currentMoveDirection.equals(MOVE_LEFT))
                    _robotFacing = CellId.ROBOT_FACING_RIGHT;
                else
                    _robotFacing = CellId.ROBOT_FACING_LEFT;
            }
            break;
            case CellId.ROBOT_FACING_LEFT:
            {
                if (_currentMoveDirection.equals(MOVE_LEFT))
                    _robotFacing = CellId.ROBOT_FACING_DOWN;
                else
                    _robotFacing = CellId.ROBOT_FACING_UP;
            }
            break;
            case CellId.ROBOT_FACING_RIGHT:
            default:
            {
                if (_currentMoveDirection.equals(MOVE_LEFT))
                    _robotFacing = CellId.ROBOT_FACING_UP;
                else
                    _robotFacing = CellId.ROBOT_FACING_DOWN;
            }
            break;
        }
    }

    private final Coordinate rightCoordinate (Coordinate coord)
    {
        int x = coord.getX();
        int y = coord.getY();

        System.out.println("rightCoordinate facing: "+_robotFacing+" and position: "+coord);

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

    private final Coordinate leftCoordinate (Coordinate coord)
    {
        int x = coord.getX();
        int y = coord.getY();

        System.out.println("leftCoordinate facing: "+_robotFacing+" and position: "+coord);

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

    private Map _theMap;
    private Trail _robotTrack;
    private Stack<String> _path;
    private String _robotFacing;
    private String _currentMoveDirection;
    private Coordinate _currentPosition;
    private boolean _debug;
}