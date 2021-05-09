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
         * 
         * Check it's 1 before changing? Maybe add a test-and-set/override?
         */

        _computer.changeInstruction(OVERRIDE_LOCATION, OVERRIDE_CODE);
    }

    public void setMainMovementRoutine (String sequence)
    {
        _computer.setInput(sequence+"\n");

        _computer.executeUntilInput();
    }

    public void setFunctions (String funcA, String funcB, String funcC)
    {
        _computer.setInput(funcA+"\n"+funcB+"\n"+funcC+"\n");

        _computer.executeUntilInput();
    }

    public void setContinuousVideo (boolean video)
    {
        if (video)
            _computer.setInput("y\n");
        else
            _computer.setInput("n\n");
    }

    public void sweep ()
    {
        _computer.executeProgram();
    }

    private Map _theMap;
    private Intcode _computer;
    private boolean _debug;
}