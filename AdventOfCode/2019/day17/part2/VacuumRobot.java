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
        _debug = debug;

        convertToPath(scannedLines);
    }

    private void convertToPath (String[] lines)
    {
        int lineLength = lines[0].length(); // all lines are the same length

        for (int y = 0; y < lines.length -1; y++)
        {
            for (int x = 0; x < lineLength; x++)
            {
                boolean isScaffold = (lines[y].charAt(x) == Scaffold.SCAFFOLDING_CHAR);
                Cell theCell = new Cell(new Coordinate(x, y), isScaffold, _debug);

                if (_debug)
                    System.out.println(theCell);

                _thePath.add(theCell);
            }
        }
    }

    private Vector<Cell> _thePath;
    private boolean _debug;
}