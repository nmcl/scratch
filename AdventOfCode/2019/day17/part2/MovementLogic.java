import java.util.*;

/*
 * Commands: L,4
Commands: L,4
Commands: L,6
Commands: R,10
Commands: L,6

== A

Commands: L,4
Commands: L,4
Commands: L,6
Commands: R,10
Commands: L,6

== A

Commands: L,12
Commands: L,6
Commands: R,10
Commands: L,6
Commands: R,8
Commands: R,10
Commands: L,6

== B

Commands: R,8
Commands: R,10
Commands: L,6

== C

Commands: L,4
Commands: L,4
Commands: L,6
Commands: R,10
Commands: L,6

== A

Commands: R,8
Commands: R,10
Commands: L,6

== C

Commands: L,12
Commands: L,6
Commands: R,10
Commands: L,6
Commands: R,8
Commands: R,10
Commands: L,6

== B

Commands: L,12
Commands: L,6
Commands: R,10
Commands: L,6

== B
 */

public class MovementLogic
{
    public static final int NUMBER_OF_FUNCTIONS = 3;

    public static final int ROUTINE_A = 0;
    public static final int ROUTINE_B = 1;
    public static final int ROUTINE_C = 2;

    public static final int MAX_CHARACTERS = 20;

    private static final int MOVED_OK = 0;
    private static final int MOVE_FINISHED = 1;
    private static final int DIRECTION_BLOCKED = 2;
    private static final int MOVE_ERROR = 3;
    private static final int LOCATION_VISITED = 4;

    public MovementLogic (Map theMap, boolean debug)
    {
        _theMap = theMap;
        _robotTrack = new Trail(_theMap);
        _path = new Stack<String>();
        _routeTaken = "";
        _robotFacing = CellId.ROBOT_FACING_UP;
        _currentMoveDirection = "";
        _currentPosition = new Coordinate(_theMap.findStartingPoint());
        _debug = debug;
    }

    public void createMovementFunctions ()
    {
        createPath();

        /*
         * Now convert the path into a series of commands
         * such as L,4 or R,8.
         */

        Vector<String> commands = getCommands();

        /*
         * Now turn the series of commands into functions
         * A, B and C based on repeated commands.
         * 
         * There are only 3 possible functions.
         * This means one function always starts at the beginning.
         * One function always ends at the ending (!) assuming it's not a repeat
         * of the first.
         * Then using both the first and the last fragment to find the third
         * and split the entire sequence into functions.
         * 
         * Also it's possible to give the Robot more commands than it can
         * execute before it gets to end of the route.
         */

        String fullCommand = "";

        for (int i = commands.size() -1; i >= 0; i--)
        {
            fullCommand += commands.elementAt(i);
        }

        System.out.println("Full command "+fullCommand);

        String[] functions = new String[NUMBER_OF_FUNCTIONS];

        /*
         * Find repeated strings. Assume minimum of 2 commands.
         */

        System.out.println("got "+getFunction(commands, fullCommand, 0, 2));
    }

    public void createMovementRoutine ()
    {
        
    }

    private String getFunction (Vector<String> commands, String fullCommand, int start, int numberOfCommands)
    {
        String repeat = getCommandString(commands, start, numberOfCommands);

        if (fullCommand.contains(repeat))
        {
            System.out.println("Repeat: "+repeat);

            String next = getFunction(commands, fullCommand, start, numberOfCommands +1);

            if (next == null)
                return repeat;
            else
                return next;
        }
        else
            System.out.println("Does not repeat: "+repeat);

        return null;
    }

    private String getCommandString (Vector<String> commands, int start, int number)
    {
        String str = "";

        for (int i = start; i < number; i++)
        {
            str += commands.elementAt(i);
        }

        return str;
    }

    private Vector<String> getCommands ()
    {
        Vector<String> commands = new Vector<String>(_path.size());
        String pathElement = null;

        /*
         * Pop the track to reverse it and get commands from the
         * starting position.
         */

        do
        {
            try
            {
                pathElement = _path.pop();

                String str = pathElement.charAt(0)+","+pathElement.length();

                commands.add(str);
            }
            catch (Exception ex)
            {
                pathElement = null;
            }

        } while (pathElement != null);

        if (_debug)
        {
            for (int i = commands.size() -1; i >= 0; i--)
            {
                System.out.println("Commands: "+commands.elementAt(i));
            }
        }

        return commands;
    }

    /*
     * Create path using only L and R.
     */

    private int createPath ()
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
        System.out.println("tryMove: "+coord+" with direction: "+direction);
        System.out.println("and current position: "+_currentPosition);

        /*
         * Already visited? Might be sufficient to just check .path
         */

        if (_robotTrack.visited(coord) && !_robotTrack.path(coord))
        {
            System.out.println("Robot already visited this location.");

            return LOCATION_VISITED;
        }

        System.out.println("Location not visited ... yet.");

        if (_theMap.isScaffold(coord))
        {
            System.out.println("Is scaffolding!");

            _currentMoveDirection = direction;

            System.out.println("Moving "+_currentMoveDirection);

            _routeTaken += _currentMoveDirection;

            _robotTrack.changeElement(coord, _currentMoveDirection);
            _currentPosition = coord;

            System.out.println("\n"+_robotTrack);
        }
        else
        {
            System.out.println("Not scaffolding!");

            System.out.println("Robot was facing "+_robotFacing+" and moving "+direction);

            _path.push(_routeTaken);

            System.out.println("Pushing: "+_routeTaken);
    
            _routeTaken = "";

            if (_theMap.theEnd(_currentPosition))
                return MOVE_FINISHED;

            changeFacing();

            System.out.println("Robot now facing "+_robotFacing);

            String nextDirection = getNextDirection();

            System.out.println("Next direction to try with new facing: "+nextDirection);

            direction = nextDirection;
        }

        if (CellId.MOVE_LEFT.equals(direction))
            coord = leftCoordinate(_currentPosition);
        else
            coord = rightCoordinate(_currentPosition);

        return tryToMove(direction, coord);
    }

    private String getNextDirection ()
    {
        System.out.println("Getting next direction to move from: "+_currentPosition);

        Coordinate coord = leftCoordinate(_currentPosition);

        System.out.println("Left coordinate would be: "+coord);

        if (_robotTrack.visited(coord) || !_robotTrack.isScaffold(coord))
        {
            System.out.println("Visited so try right ...");

            coord = rightCoordinate(_currentPosition);

            System.out.println("Right coordinate would be: "+coord);

            if (_robotTrack.visited(coord))
                return null;
            else
                return CellId.MOVE_RIGHT;
        }
        else
        {
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
    private boolean _debug;
}