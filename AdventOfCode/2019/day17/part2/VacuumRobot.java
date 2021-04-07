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

    private void convertToPath (String[] scannedLines)
    {

    }

    private Vector<Cell> _thePath;
    private boolean _debug;
}