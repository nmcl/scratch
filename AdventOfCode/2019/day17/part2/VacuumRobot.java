import java.util.*;

public class VacuumRobot
{
    // instructions

    public static final String LEFT_COMMAND = "L";
    public static final String RIGHT_COMMAND = "R";
    public static final String NEW_LINE = "\n";

    private static final String INITIAL_INPUT = "";
    private static final String OVERRIDE_CODE = "2";

    private static final int OVERRIDE_LOCATION = 0;

    public VacuumRobot (String[] scannedLines, Vector<String> instructions, boolean debug)
    {
        _theMap = new Vector<Cell>();
        _maxX = 0;
        _maxY = 0;
        _computer = new Intcode(instructions, INITIAL_INPUT, debug);
        _debug = debug;

        createScaffolding(scannedLines);

        /*
         * "Force the vacuum robot to wake up by changing the value in your
         * ASCII program at address 0 from 1 to 2."
         */

        _computer.changeInstruction(OVERRIDE_LOCATION, OVERRIDE_CODE);
    }

    public void printCells ()
    {
        Enumeration<Cell> iter = _theMap.elements();
        int index = 1;

        System.out.println("Dimensions < "+_maxX+", "+_maxY+" >");

        while (iter.hasMoreElements())
        {
            System.out.print(iter.nextElement().getContents());

            if (index++ == _maxX)
            {
                System.out.println();

                index = 1;
            }
        }
    }

    private void createScaffolding (String[] lines)
    {
        int lineLength = lines[0].length(); // all lines are the same length

        _maxY = lines.length;
        _maxX = lineLength;

        for (int y = 0; y < lines.length -1; y++)
        {
            for (int x = 0; x < lineLength; x++)
            {
                Cell theCell = new Cell(new Coordinate(x, y), String.valueOf(lines[y].charAt(x)), _debug);

                if (_debug)
                    System.out.println(theCell);

                    _theMap.add(theCell);
            }
        }
    }

    private Vector<Cell> _theMap;
    private int _maxX;
    private int _maxY;
    private Intcode _computer;
    private boolean _debug;
}