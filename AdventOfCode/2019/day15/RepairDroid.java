import java.util.*;

public class RepairDroid
{
    public static final String INITIAL_INPUT = Integer.toString(DroidMovement.NORTH);

    public RepairDroid (Vector<String> instructions, boolean debug)
    {
        _debug = debug;
        _theComputer = new Intcode(instructions, INITIAL_INPUT, _debug);
        _currentLocation = new Coordinate(0, 0);  // starting location
        _theMap = new Maze();

        _theMap.addContent(_currentLocation, TileId.TRAVERSE);
    }

    public final int moveToOxygenStation ()
    {
        int numberOfSteps = 0;

        // create a map first!

        explore(_currentLocation);

        return numberOfSteps;
    }

    public void printGrid ()
    {
        System.out.println(_theMap);
    }

    /*
     * If we run into a wall then try a different direction.
     * If we can't move other than backwards then do that.
     * Don't move into areas we've already been.
     */
    
    private boolean explore ()
    {
        while (!_theComputer.hasHalted())
        {
            Coordinate[] moves = getNextPositions();  // get all possible moves (Coordinates)

            System.out.println("\n"+_theMap);

            /*
             * We search N, E, S and then W.
             */

            if (!tryToMove(String.valueOf(DroidMovement.NORTH), moves[0]))
            {
                System.out.println("**Failed to move NORTH");

                if (!tryToMove(String.valueOf(DroidMovement.EAST), moves[1]))
                {
                    System.out.println("**Failed to move EAST");

                    if (!tryToMove(String.valueOf(DroidMovement.SOUTH), moves[2]))
                    {
                        System.out.println("**Failed to move SOUTH");

                        if (!tryToMove(String.valueOf(DroidMovement.WEST), moves[3]))
                        {
                            System.out.println("**Failed to move WEST");
                        }
                    }
                }
            }
        }

        return _theMap.isOxygenStation(_currentLocation);
    }

    private boolean tryToMove (String direction, Coordinate to)
    {                
        System.out.println("**Trying to move from: "+_currentLocation+" to "+to+" with direction "+DroidMovement.toString(direction));

        // if we've already been there then don't move!

        if (_theMap.isExplored(to))
        {
            System.out.println("**Been there already.");

            return false;
        }

        _theComputer.setInput(direction);
        _theComputer.executeUntilInput();

        if (_theComputer.hasOutput())
        {
            int response = Integer.parseInt(_theComputer.getOutput());

            System.out.println("**Response is "+DroidStatus.toString(response));

            switch (response)
            {
                case DroidStatus.ARRIVED:  // arrived at the station!!
                {
                    _theMap.addContent(to, TileId.OXYGEN_STATION);
                    _currentLocation = to;

                    return true;
                }
                case DroidStatus.COLLISION:
                {
                    _theMap.addContent(to, TileId.WALL);  // didn't move as we hit a wall

                    return false;
                }
                case DroidStatus.MOVED:
                {
                    /*
                     * Droid moved so let's try to move again.
                     */

                    _theMap.addContent(to, TileId.TRAVERSE);
                    _currentLocation = to;

                    return explore();
                }
                default:
                    System.out.println("Unknown response: "+response);
            }
        }
        else
            System.out.println("Error - no output after move instruction!");

        return false;
    }

    private final Coordinate[] getNextPositions ()
    {
        Coordinate[] coords = new Coordinate[4];

        // N, E, S, W

        coords[0] = new Coordinate(_currentLocation.getX(), coord.getY() -1);
        coords[1] = new Coordinate(_currentLocation.getX() -1, coord.getY());
        coords[2] = new Coordinate(_currentLocation.getX(), coord.getY() +1);
        coords[3] = new Coordinate(_currentLocation.getX() +1, coord.getY());

        return coords;
    }

    private boolean _debug;
    private Intcode _theComputer;
    private Coordinate _currentLocation;
    private Maze _theMap;
}