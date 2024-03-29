import java.util.*;

public class MovementLogic
{
    private static final int MOVED_OK = 0;
    private static final int MOVE_FINISHED = 1;
    private static final int DIRECTION_BLOCKED = 2;
    private static final int MOVE_ERROR = 3;
    private static final int LOCATION_VISITED = 4;

    public static final int NUMBER_OF_FUNCTIONS = 3;

    public MovementLogic (Map theMap, boolean debug)
    {
        _theMap = theMap;
        _robotTrack = new Trail(_theMap);
        _path = new Stack<String>();
        _routeTaken = "";
        _robotFacing = CellId.ROBOT_FACING_UP;
        _currentMoveDirection = "";
        _currentPosition = new Coordinate(_theMap.findStartingPoint());
        _fullCommand = "";
        _debug = debug;
    }

    public Vector<MovementFunction> createMovementFunctions ()
    {
        createPath();

        FunctionRoutine routine = new FunctionRoutine(_path, _debug);

        _fullCommand = routine.getCommandString();
        
        return routine.createMovementFunctions();
    }

    public String getMainRoutine (Vector<MovementFunction> functions)
    {
        MovementRoutine mr = new MovementRoutine(_fullCommand, functions, _debug);

        return mr.getMainRoutine();
    }

    /*
     * Create path using only L and R.
     */

    private int createPath ()
    {
        if (_debug)
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

            if (tryToMove(CellId.MOVE_LEFT, leftCoordinate(_currentPosition)) != MOVE_FINISHED)
            {
                return tryToMove(CellId.MOVE_RIGHT, rightCoordinate(_currentPosition));
            }
            else
                return MOVE_FINISHED;
        }
        else
        {
            System.out.println("Robot not found!");

            return MOVE_ERROR;
        }
    }

    private int tryToMove (String direction, Coordinate coord)
    {
        if (_debug)
        {
            System.out.println("tryMove: "+coord+" with direction: "+direction);
            System.out.println("and current position: "+_currentPosition);
        }

        /*
         * Already visited? Might be sufficient to just check .path
         */

        if (_robotTrack.visited(coord) && !_robotTrack.path(coord))
        {
            if (_debug)
                System.out.println("Robot already visited this location.");

            return LOCATION_VISITED;
        }

        if (_debug)
            System.out.println("Location not visited ... yet.");

        if (_theMap.isScaffold(coord))
        {
            if (_debug)
                System.out.println("Is scaffolding!");

            _currentMoveDirection = direction;
            _routeTaken += _currentMoveDirection;

            _robotTrack.changeElement(coord, _currentMoveDirection);
            _currentPosition = coord;

            if (_debug)
                System.out.println("\n"+_robotTrack);
        }
        else
        {
            if (_debug)
            {
                System.out.println("Not scaffolding!");

                System.out.println("Robot was facing "+_robotFacing+" and moving "+direction);
            }

            _path.push(_routeTaken);

            _routeTaken = "";

            if (_theMap.theEnd(_currentPosition))
                return MOVE_FINISHED;

            changeFacing();

            if (_debug)
                System.out.println("Robot now facing "+_robotFacing);

            direction = getNextDirection();
        }

        if (CellId.MOVE_LEFT.equals(direction))
            coord = leftCoordinate(_currentPosition);
        else
            coord = rightCoordinate(_currentPosition);

        return tryToMove(direction, coord);
    }

    private String getNextDirection ()
    {
        if (_debug)
            System.out.println("Getting next direction to move from: "+_currentPosition);

        Coordinate coord = leftCoordinate(_currentPosition);

        if (_debug)
            System.out.println("Left coordinate would be: "+coord);

        if (_robotTrack.visited(coord) || !_robotTrack.isScaffold(coord))
        {
            if (_debug)
                System.out.println("Visited so try right ...");

            coord = rightCoordinate(_currentPosition);

            if (_debug)
                System.out.println("Right coordinate would be: "+coord);

            if (_robotTrack.visited(coord))
                return null;
            else
                return CellId.MOVE_RIGHT;
        }
        else
        {
            if (_debug)
                System.out.println("Not visited.");

            return CellId.MOVE_LEFT;
        }
    }

    private void changeFacing ()
    {
        switch (_robotFacing)
        {
            case CellId.ROBOT_FACING_UP:
            {
                if (_currentMoveDirection.equals(CellId.MOVE_LEFT))
                    _robotFacing = CellId.ROBOT_FACING_LEFT;
                else
                    _robotFacing = CellId.ROBOT_FACING_RIGHT;
            }
            break;
            case CellId.ROBOT_FACING_DOWN:
            {
                if (_currentMoveDirection.equals(CellId.MOVE_LEFT))
                    _robotFacing = CellId.ROBOT_FACING_RIGHT;
                else
                    _robotFacing = CellId.ROBOT_FACING_LEFT;
            }
            break;
            case CellId.ROBOT_FACING_LEFT:
            {
                if (_currentMoveDirection.equals(CellId.MOVE_LEFT))
                    _robotFacing = CellId.ROBOT_FACING_DOWN;
                else
                    _robotFacing = CellId.ROBOT_FACING_UP;
            }
            break;
            case CellId.ROBOT_FACING_RIGHT:
            default:
            {
                if (_currentMoveDirection.equals(CellId.MOVE_LEFT))
                    _robotFacing = CellId.ROBOT_FACING_UP;
                else
                    _robotFacing = CellId.ROBOT_FACING_DOWN;
            }
            break;
        }
    }

    /*
     * Map/Trail can deal with invalid Coordinates.
     */

    private final Coordinate rightCoordinate (Coordinate coord)
    {
        int x = coord.getX();
        int y = coord.getY();

        if (_debug)
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

        if (_debug)
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
    private String _routeTaken;
    private String _robotFacing;
    private String _currentMoveDirection;
    private Coordinate _currentPosition;
    private String _fullCommand;
    private boolean _debug;
}