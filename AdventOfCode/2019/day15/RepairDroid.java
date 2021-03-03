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

    private void recursiveSearch (Coordinate from)
    {
        boolean found = false;

        
        return numberOfSteps;
    }

    private boolean _debug;
    private Intcode _theComputer;
    private Coordinate _location;
    private Map _theMap;
}