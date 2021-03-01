public class RepairDroid
{
    public static final String INITIAL_INPUT = "";

    public RepairDroid (Vector<String> instructions, boolean debug)
    {
        _debug = debug;
        _theComputer = new Intcode(instructions, INITIAL_INPUT, _debug);
        _location = new Coordinate(0, 0);
    }

    public final int moveToOxygenStation ()
    {
        int numberOfSteps = 0;

        return numberOfSteps;
    }

    public void printGrid ()
    {
        
    }

    private boolean _debug;
    private Intcode _theComputer;
    private Coordinate _location;
}