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
        if (_currentPosition != null)
        {
            /*
             * Try L then R.
             * 
             * Robot always starts facing up. Don't change facing until
             * we run into a "wall" (open space, i.e., scaffolding).
             */

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

        System.out.println("tryMoveLeft: "+coord);

        if (_theMap.isScaffold(coord))
        {
            _currentMoveDirection = MOVE_LEFT;
            _path.push(_currentMoveDirection);

            return tryMoveLeft(coord);
        }
        else
        {
            System.out.println("Left change facing! "+curr);

            return changeDirection(curr);
        }
    }

    private boolean tryMoveRight (Coordinate curr)
    {
        // go as far right as we can from current location

        Coordinate coord = rightCoordinate(curr);

        System.out.println("tryMoveRight: "+coord);

        if (_theMap.isScaffold(coord))
        {
            _currentMoveDirection = MOVE_RIGHT;
            _path.push(_currentMoveDirection);
            _currentPosition = coord;

            return tryMoveRight(coord);
        }
        else
        {
            System.out.println("Right change facing! "+curr);
            
            return changeDirection(curr);
        }
    }

    private final boolean changeDirection (Coordinate coord)
    {
        /*
         * Look at current move direction/heading. Then change
         * by checking R, L. The scaffolding map is such that we
         * don't need to worry about infinit loops and backtracking.
         */

         System.out.println("Changing direction at: "+coord);

         if (_currentMoveDirection.equals(MOVE_LEFT))
         {
            Coordinate nextPosition = leftCoordinate(coord);

            System.out.println("Next (left) position could be: "+nextPosition);

            if (_theMap.isScaffold(nextPosition))
            {
                _robotFacing = changeFacing(_robotFacing, true, true);
                _currentPosition = nextPosition;
            }
            else
            {
                _robotFacing = changeFacing(_robotFacing, true, false);
                _currentPosition = rightCoordinate(coord);
            }
         }
         else
         {
            Coordinate nextPosition = rightCoordinate(coord);

            System.out.println("Next (right) position could be: "+nextPosition);

            if (_theMap.isScaffold(nextPosition))
            {
                _robotFacing = changeFacing(_robotFacing, false, true);
                _currentPosition = nextPosition;
            }
            else
            {
                _robotFacing = changeFacing(_robotFacing, false, false);
                _currentPosition = rightCoordinate(coord);
            }
         }

         return createPath();
    }

    private final String changeFacing (String facing, boolean wasMovingLeft, boolean faceLeft)
    {
        String nFacing = null;

        switch (facing)
        {
            case CellId.ROBOT_FACING_DOWN:
            {
                if (wasMovingLeft)
                {
                    if (faceLeft)
                        nFacing = CellId.ROBOT_FACING_UP;
                    else
                        nFacing = CellId.ROBOT_FACING_DOWN;
                }
                else
                {
                    if (faceLeft)
                        nFacing = CellId.ROBOT_FACING_DOWN;
                    else
                        nFacing = CellId.ROBOT_FACING_UP;
                }
            }
            break;
            case CellId.ROBOT_FACING_UP:
            {
                if (wasMovingLeft)
                {
                    if (faceLeft)
                        nFacing = CellId.ROBOT_FACING_DOWN;
                    else
                        nFacing = CellId.ROBOT_FACING_UP;
                }
                else
                {
                    if (faceLeft)
                        nFacing = CellId.ROBOT_FACING_UP;
                    else
                        nFacing = CellId.ROBOT_FACING_DOWN;
                }
            }
            break;
            case CellId.ROBOT_FACING_LEFT:
            {
                if (wasMovingLeft)
                {
                    if (faceLeft)
                        nFacing = CellId.ROBOT_FACING_RIGHT;
                    else
                        nFacing = CellId.ROBOT_FACING_LEFT;
                }
                else
                {
                    if (faceLeft)
                        nFacing = CellId.ROBOT_FACING_LEFT;
                    else
                        nFacing = CellId.ROBOT_FACING_RIGHT;
                }
            }
            break;
            case CellId.ROBOT_FACING_RIGHT:
            default:
            {
                if (wasMovingLeft)
                {
                    if (faceLeft)
                        nFacing = CellId.ROBOT_FACING_LEFT;
                    else
                        nFacing = CellId.ROBOT_FACING_RIGHT;
                }
                else
                {
                    if (faceLeft)
                        nFacing = CellId.ROBOT_FACING_RIGHT;
                    else
                        nFacing = CellId.ROBOT_FACING_LEFT;
                }
            }
            break;
        }

        return nFacing;
    }

    private final Coordinate rightCoordinate (Coordinate coord)
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

    private final Coordinate leftCoordinate (Coordinate coord)
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
    private String _currentMoveDirection;
    private Coordinate _currentPosition;
    private boolean _debug;
}