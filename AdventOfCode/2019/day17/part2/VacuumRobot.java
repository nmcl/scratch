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

    public VacuumRobot (Map map, Vector<String> instructions, boolean debug)
    {
        _theMap = map;
        _computer = new Intcode(instructions, INITIAL_INPUT, debug);
        _debug = debug;

        /*
         * "Force the vacuum robot to wake up by changing the value in your
         * ASCII program at address 0 from 1 to 2."
         */

        _computer.changeInstruction(OVERRIDE_LOCATION, OVERRIDE_CODE);
    }

    private Map _theMap;
    private Intcode _computer;
    private boolean _debug;
}