import java.util.*;

public class VacuumRobot
{
    // instructions

    public static final String LEFT_COMMAND = "L";
    public static final String RIGHT_COMMAND = "R";
    public static final String NEW_LINE = "\n";

    public VacuumRobot (String[] scannedLines, boolean debug)
    {
        _thePath = new Vector<Cell>();
        _maxX = 0;
        _maxY = 0;
        _debug = debug;

        convertToPath(scannedLines);
    }

    public void printCells ()
    {
        Enumeration<Cell> iter = _thePath.elements();
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

    private void convertToPath (String[] lines)
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

                _thePath.add(theCell);
            }
        }
    }

    private Vector<Cell> _thePath;
    private int _maxX;
    private int _maxY;
    private boolean _debug;
}