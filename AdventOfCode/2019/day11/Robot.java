import java.util.*;

public class Robot
{
    public static final char UP = '^';
    public static final char DOWN = 'v';
    public static final char LEFT = '<';
    public static final char RIGHT = '>';

    public static final int DEFAULT_START_X = 0;
    public static final int DEFAULT_START_Y = 0;

    public static final int NUMBER_OF_PANELS_TO_MOVE = 1;

    public Robot (Vector<String> instructions, int x, int y, boolean debug)
    {
        // any input instructions at this point should be provided 0

        _theComputer = new Intcode(instructions, 0, debug);
        _debug = debug;
        _currentDirection = UP;  // The robot starts facing up.
        _currentPanel = new Panel(x, y);
    }

    public Robot (Vector<String> instructions, boolean debug)
    {
        this(instructions, DEFAULT_START_X, DEFAULT_START_Y, debug);
    }

    public void paint ()
    {

    }

    public final char currentDirection ()
    {
        return _currentDirection;
    }

    @Override
    public String toString ()
    {
        return "Robert current direction: "+_currentDirection+" and current panel: "+_currentPanel;
    }

    private Intcode _theComputer;
    private boolean _debug;
    private char _currentDirection;
    private Panel _currentPanel;
}