import java.util.*;

public class RepairDroid
{
    public static final String INITIAL_INPUT = Integer.toString(DroidMovement.NORTH);

    public RepairDroid (Vector<String> instructions, boolean debug)
    {
        _debug = debug;
        _theComputer = new Intcode(instructions, INITIAL_INPUT, _debug);
        _location = new Coordinate(0, 0);  // starting location
        _theMap = new Map();

        _theMap.addContent(_location, TileId.TRAVERSE);
    }

    public final int moveToOxygenStation ()
    {
        int numberOfSteps = 0;

        // create a map first!

        return numberOfSteps;
    }

    public void printGrid ()
    {
        
    }

    private boolean recursiveSearch (Coordinate from)
    {
        while (!_theComputer.hasHalted())
        {
            Coordinate coord = new Coordinate(from.getX(), from.getY()+1);

            _theComputer.setInput(new String(DroidMovement.NORTH));

            _theComputer.singleStepExecution();

            if (_theComputer.hasOutput())
            {
                int response = Integer.parseInt(_theComputer.getOutput());

                switch (response)
                {
                    case DroidStatus.ARRIVED:
                    {
                        _theMap.addContent(coord, TileId.OXYGEN_STATION);
                        
                        return true;
                    }
                    break;
                    case DroidStatus.COLLISION:
                    {
                        _theMap.addContent(from, TileId.WALL);  // didn't move as we hit a wall

                        return false;
                    }
                    break;
                    case DroidStatus.MOVED:
                    {
                        _theMap.addContent(coord, TileId.TRAVERSE);

                        return recursiveSearch(coord);
                    }
                    break;
                    default:
                        System.out.println("Unknown response: "+response);
                }
            }
            else
                System.out.println("Error - no output after move instruction!");
        }

        return false;
    }

    private boolean _debug;
    private Intcode _theComputer;
    private Coordinate _location;
    private Map _theMap;
}