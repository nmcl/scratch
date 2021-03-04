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

        _visited = null;
    }

    public final int moveToOxygenStation ()
    {
        int numberOfSteps = 0;

        _visited = new ArrayList<Coordinate>();

        // create a map first!

        recursiveSearch(_currentLocation);

        return numberOfSteps;
    }

    public void printGrid ()
    {
        System.out.println(_theMap);
    }

    private boolean recursiveSearch (Coordinate from)
    {
        /*
                if (_theMap.isWall(from) || !_theMap.isUnexplored(from))
            return false;
        
        _currentLocation = from;
        _visited.add(_currentLocation);

        _theMap.addContent(_currentLocation, TileId.TRAVERSE);

        if (_theMap.isOxygenStation(from))
            return true;

            */
            
        //while (!_theComputer.hasHalted())
        {
            Coordinate to = new Coordinate(from.getX(), from.getY()+1);

            System.out.println("\n"+_theMap);

            if (!tryToMove(String.valueOf(DroidMovement.NORTH), from, to))
            {
                to = new Coordinate(from.getX(), from.getY()-1);

                System.out.println("\n"+_theMap);

                if (!tryToMove(String.valueOf(DroidMovement.SOUTH), from, to))
                {
                    to = new Coordinate(from.getX()+1, from.getY());

                    System.out.println("\n"+_theMap);

                    if (!tryToMove(String.valueOf(DroidMovement.EAST), from, to))
                    {
                        to = new Coordinate(from.getX()-1, from.getY());

                        System.out.println("\n"+_theMap);

                        return tryToMove(String.valueOf(DroidMovement.WEST), from, to);
                    }
                }
            }
        }

        return false;
    }

    private boolean tryToMove (String direction, Coordinate from, Coordinate to)
    {
        System.out.println("**Trying to move from: "+from+" to "+to+" with direction "+DroidMovement.toString(direction));

        _theComputer.setInput(direction);

        do
        {
            System.out.println("**Single step execution!");

            _theComputer.singleStepExecution();
        }
        while (!_theComputer.waitingForInput());

        System.out.println("**Waiting for input and output is: "+_theComputer.hasOutput());

        if (_theComputer.hasOutput())
        {
            int response = Integer.parseInt(_theComputer.getOutput());

            System.out.println("**Response is "+DroidStatus.toString(response));

            switch (response)
            {
                case DroidStatus.ARRIVED:
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
                    _theMap.addContent(to, TileId.TRAVERSE);
                    _currentLocation = to;

                    return recursiveSearch(to);
                }
                default:
                    System.out.println("Unknown response: "+response);
            }
        }
        else
            System.out.println("Error - no output after move instruction!");

        return false;
    }

    private final Coordinate[] getNextPositions (Coordinate coord)
    {
        Coordinate[] coords = new Coordinate[4];

        coords[0] = new Coordinate(coord.getX(), coord.getY() -1);
        coords[1] = new Coordinate(coord.getX(), coord.getY() +1);
        coords[2] = new Coordinate(coord.getX() -1, coord.getY());
        coords[3] = new Coordinate(coord.getX() +1, coord.getY());

        return coords;
    }

    private boolean _debug;
    private Intcode _theComputer;
    private Coordinate _currentLocation;
    private Maze _theMap;
    private List<Coordinate> _visited;
}