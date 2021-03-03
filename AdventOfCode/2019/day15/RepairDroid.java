import java.util.*;

public class RepairDroid
{
    public static final String INITIAL_INPUT = Integer.toString(DroidMovement.NORTH);

    public RepairDroid (Vector<String> instructions, boolean debug)
    {
        _debug = debug;
        _theComputer = new Intcode(instructions, INITIAL_INPUT, _debug);
        _location = new Coordinate(0, 0);
        _visitedLocations = new Vector<Coordinate>();

        _visitedLocations.add(_location);
    }

    public final int moveToOxygenStation ()
    {
        int numberOfSteps = 0;
        boolean found = false;

        while (!found)
        {

        }

        return numberOfSteps;
    }

    public void printGrid ()
    {
        
    }

    private boolean _debug;
    private Intcode _theComputer;
    private Coordinate _location;
    private Vector<Coordinate> _visitedLocations;
}