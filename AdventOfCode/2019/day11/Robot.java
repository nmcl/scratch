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

    public static final String WHITE = " ";
    public static final String BLACK = "x";

    public Robot (Vector<String> instructions, boolean debug)
    {
        this(instructions, DEFAULT_START_X, DEFAULT_START_Y, debug);
    }

    public Robot (Vector<String> instructions, int x, int y, boolean debug)
    {
        // any input instructions at this point should be provided 0

        _theComputer = new Intcode(instructions, 0, debug);
        _debug = debug;
        _currentDirection = UP;  // The robot starts facing up.
        _currentPanel = new Panel(x, y);
        _panelsPainted = new Vector<Panel>();
        _maxX = 0;
        _minX = 0;
        _maxY = 0;
        _minY = 0;

        _panelsPainted.add(_currentPanel);
    }

    public int paint ()
    {
        String output = null;
        boolean paintInstruction = true;
        int numberOfInstructions = 0;

        System.out.println("**starting at "+_currentPanel.getPosition());

        while (!_theComputer.hasHalted())
        {
            System.out.println("**current position "+_currentPanel.getPosition());

            if (numberOfInstructions == 0)
                System.out.println("**initial computer input 0");

            numberOfInstructions++;
            System.out.println("**instruction number: "+numberOfInstructions);  

            _theComputer.executeProgram(); 

            if (_theComputer.hasOutput())
            {
                output = _theComputer.getOutput();
                System.out.println("**computer output "+output);
            }
            else
                System.out.println("**NO OUTPUT");

            if (output != null)
            {
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

                if (paintInstruction) // which output is this?
                {
                    paintPanel(output);
                    paintInstruction = false;
                }
                else
                {
                    moveRobot(output);
                    paintInstruction = true;
                }

                //printPath();

                output = null;
            }
        }

        return _panelsPainted.size();
    }

    public final char currentDirection ()
    {
        return _currentDirection;
    }

    @Override
    public String toString ()
    {
        return "Robot current direction: "+_currentDirection+" and current panel: "+_currentPanel;
    }

    public void printPath ()
    {
        //System.out.println("**printing <"+_minX+", "+_maxX+"> and <"+_minY+", "+_maxY+">");
         
        for (int x = _minX; x <= _maxX; x++)
        {
            for (int y = _minY; y < _maxY; y++)
            {
                Panel p = new Panel(x, y);

                if (_panelsPainted.contains(p))
                    System.out.print(WHITE);
                else
                    System.out.println(BLACK);
            }

            System.out.println();
        }
    }

    private void paintPanel (String colour)
    {
        int theColour = Integer.parseInt(colour);
        
        if (_debug)
            System.out.println("Got instruction to paint panel "+ ((theColour == Panel.BLACK) ? "black" : "white"));

       if (theColour == Panel.BLACK)
            _currentPanel.paint(Panel.BLACK);
        else
            _currentPanel.paint(Panel.WHITE);
    }

    private void moveRobot (String direction)
    {
        int newDirection = Integer.parseInt(direction);
        Coordinate currentPosition = _currentPanel.getPosition();
        int xCoord = currentPosition.getX();
        int yCoord = currentPosition.getY();

        if (_debug)
            System.out.println("Got instruction to move "+newDirection);

        // figure out which direction we're supposed to point in next

        switch (_currentDirection)
        {
            case UP:
            {
                if (newDirection == LEFT_TURN_90)
                {
                    _currentDirection = LEFT;
                    xCoord--;
                }
                else
                {
                    _currentDirection = RIGHT;
                    xCoord ++;
                }
            }
            break;
            case DOWN:
            {
                if (newDirection == LEFT_TURN_90)
                {
                    _currentDirection = RIGHT;
                    xCoord++;
                }
                else
                {
                    _currentDirection = LEFT;
                    xCoord--;
                }
            }
            break;
            case LEFT:
            {
                if (newDirection == LEFT_TURN_90)
                {
                    _currentDirection = DOWN;
                    yCoord--;
                }
                else
                {
                    _currentDirection = UP;
                    yCoord++;
                }
            }
            break;
            case RIGHT:
            {
                if (newDirection == LEFT_TURN_90)
                {
                    _currentDirection = UP;
                    yCoord++;
                }
                else
                {
                    _currentDirection = DOWN;
                    yCoord++;
                }
            }
            break;
            default:
                System.out.println("Unknown current direction: "+_currentDirection);
                break;
        }

        if (xCoord > _maxX)
            _maxX = xCoord;
        else
        {
            if (xCoord < _minX)
                _minX = xCoord;
        }

        if (yCoord > _maxY)
            _maxY = yCoord;
        else
        {
            if (yCoord < _minY)
                _minY = yCoord;
        }

        Coordinate nextCoord = new Coordinate(xCoord, yCoord);

        // Have we passed over this panel before?

        Enumeration<Panel> iter = _panelsPainted.elements();
        Panel nextPanel = null;

        while (iter.hasMoreElements() && (nextPanel == null))
        {
            nextPanel = iter.nextElement();

            //System.out.println("**comparing "+nextCoord+" and "+nextPanel.getPosition());

            if (nextCoord.equals(nextPanel.getPosition()))
                break;
            else
                nextPanel = null;
        }

        //System.out.println("nextPanel "+nextPanel);

        if (nextPanel == null)
        {
            nextPanel = new Panel(nextCoord);
            _panelsPainted.add(nextPanel);
        }
        
        _currentPanel = nextPanel;
    }

    private Intcode _theComputer;
    private boolean _debug;
    private char _currentDirection;
    private Panel _currentPanel;
    private Vector<Panel> _panelsPainted;
    private int _maxX;
    private int _minX;
    private int _maxY;
    private int _minY;
}