public class Robot
{
    public static final char UP = '^';
    public static final char DOWN = 'v';
    public static final char LEFT = '<';
    public static final char RIGHT = '>';

    public static final int DEFAULT_START_X = 0;
    public static final int DEFAULT_START_Y = 0;

    public Robot (boolean debug, int x, int y)
    {
        _debug = debug;
        _currentDirection = UP;  // The robot starts facing up.
        _currentPanel = new Panel(x, y);
    }

    public Robot (boolean debug)
    {
        this(debug, DEFAULT_START_X, DEFAULT_START_Y);
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