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

             // only change facing when we start to move off again

            if (!tryMoveLeft(_currentPosition))
                tryMoveRight(_currentPosition);

            return true;
        }
        else
        {
            System.out.println("Robot not found!");

            return false;
        }
    }

    private boolean tryMoveLeft (Coordinate curr)
    {
        // go as far left as we can from current location

        Coordinate coord = leftCoordinate(curr);

        System.out.println("tryMoveLeft: "+coord+" with facing: "+_robotFacing);

        if (_theMap.isScaffold(coord))
        {
            _currentMoveDirection = MOVE_LEFT;
            _path.push(_currentMoveDirection);
            
            _robotTrack.changeElement(coord, _robotFacing);

            System.out.println("\n"+_robotTrack);

            return tryMoveLeft(coord);
        }
        else
        {
            System.out.println("Not scaffolding at "+coord);
            System.out.println("Left change facing! "+curr);

            _currentPosition = curr;

            System.out.print("Was facing: "+_robotFacing);

            do
            {
                changeFacing(MOVE_LEFT);

                coord = leftCoordinate(_currentPosition);

            } while (_robotTrack.visited(coord));

            System.out.println(" and now facing: "+_robotFacing);

            return createPath();
        }
    }

    private boolean tryMoveRight (Coordinate curr)
    {
        // go as far right as we can from current location

        Coordinate coord = rightCoordinate(curr);

        System.out.println("tryMoveRight: "+coord+" with facing: "+_robotFacing);
        
        if (_theMap.isScaffold(coord))
        {
            _currentMoveDirection = MOVE_RIGHT;
            _path.push(_currentMoveDirection);

            _robotTrack.changeElement(coord, _robotFacing);

            System.out.println("\n"+_robotTrack);
            
            return tryMoveRight(coord);
        }
        else
        {
            System.out.println("Not scaffolding at "+coord);
            System.out.println("Right change facing! "+curr);
            
            _currentPosition = curr;

            System.out.print("Was facing: "+_robotFacing);

            changeFacing(MOVE_RIGHT);

            System.out.println(" and now facing: "+_robotFacing);

            return createPath();
        }
    }

    private void changeFacing (String moveDirection)
    {
        switch (_robotFacing)
        {
            case CellId.ROBOT_FACING_UP:
            {
                if (moveDirection.equals(MOVE_LEFT))
                    _robotFacing = CellId.ROBOT_FACING_LEFT;
                else
                    _robotFacing = CellId.ROBOT_FACING_RIGHT;
            }
            break;
            case CellId.ROBOT_FACING_DOWN:
            {
                if (moveDirection.equals(MOVE_LEFT))
                    _robotFacing = CellId.ROBOT_FACING_RIGHT;
                else
                    _robotFacing = CellId.ROBOT_FACING_LEFT;
            }
            break;
            case CellId.ROBOT_FACING_LEFT:
            {
                if (moveDirection.equals(MOVE_LEFT))
                    _robotFacing = CellId.ROBOT_FACING_DOWN;
                else
                    _robotFacing = CellId.ROBOT_FACING_UP;
            }
            break;
            case CellId.ROBOT_FACING_RIGHT:
            default:
            {
                if (moveDirection.equals(MOVE_LEFT))
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