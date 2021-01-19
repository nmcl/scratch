import java.util.*;

public class Robot
{
    public static final char UP = '^';
    public static final char DOWN = 'v';
    public static final char LEFT = '<';
    public static final char RIGHT = '>';

    public static final int LEFT_TURN_90 = 0;
    public static final int RUGHT_TURN_90 = 1;

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
        _panelsPainted = new Stack<Panel>();

        _panelsPainted.push(_currentPanel);
    }

    public Robot (Vector<String> instructions, boolean debug)
    {
        this(instructions, DEFAULT_START_X, DEFAULT_START_Y, debug);
    }

    public void paint ()
    {
        Vector<String> output = null;

        while (!_theComputer.hasHalted())
        {
            output = _theComputer.executeProgram();

            /*
             * Should return two outputs:
             * 
             * First, it will output a value indicating the color to paint the panel the
             * robot is over: 0 means to paint the panel black, and 1 means to paint the panel white.
             * 
             * Second, it will output a value indicating the direction the robot should turn: 0 means
             * it should turn left 90 degrees, and 1 means it should turn right 90 degrees.
             * 
             * After the robot turns, it should always move forward exactly one panel. The robot
             * starts facing up.
             */

             String colour = output.get(0);
             String direction = output.get(1);

             if (Integer.parseInt(colour) == Panel.BLACK)
                _currentPanel.paint(Panel.BLACK);
            else
                _currentPanel.paint(Panel.WHITE);

            if (Integer.parseInt(direction) == LEFT_TURN_90)
            {

            }
        }
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
    Stack<Panel> _panelsPainted;
}