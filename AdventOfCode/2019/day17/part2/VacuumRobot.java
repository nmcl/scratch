import java.util.*;

public class VacuumRobot
{
    // instructions

    public static final String LEFT_COMMAND = "L";
    public static final String RIGHT_COMMAND = "R";

    private static final String INITIAL_INPUT = null;
    private static final String OVERRIDE_CODE = "2";

    private static final int OVERRIDE_LOCATION = 0;

    private static char SEPARATOR = ',';
    private static int NEW_LINE = 10;

    public VacuumRobot (Map map, Vector<String> instructions, boolean debug)
    {
        _theMap = map;
        _computer = new Intcode(instructions, INITIAL_INPUT, debug);
        _debug = debug;

        /*
         * "Force the vacuum robot to wake up by changing the value in your
         * ASCII program at address 0 from 1 to 2."
         * 
         * Should we check it's 1 before changing? Maybe add a test-and-set/override?
         */

        _computer.changeInstruction(OVERRIDE_LOCATION, OVERRIDE_CODE);
    }

    public void setMainMovementRoutine (String sequence)
    {
        String str = convertToAscii(sequence);

        System.out.println("adding "+str);

        _computer.setInput(str);

        _computer.executeUntilInput();
    }

    public void setFunctions (String funcA, String funcB, String funcC)
    {
        String funcAC = convertToAscii(funcA);
        String funcBC = convertToAscii(funcB);
        String funcCC = convertToAscii(funcC);

        System.out.println("adding "+funcAC+" "+funcBC+" "+funcCC);

        _computer.setInput(funcAC+funcBC+funcCC);

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

    private String convertToAscii (String sequence)
    {
        String converted = "";
        char[] asArray = sequence.toCharArray();

        for (int i = 0; i < asArray.length; i++)
        {
            if (asArray[i] != SEPARATOR)
                converted += (int) asArray[i];
            else
                converted += SEPARATOR;
        }

        converted += SEPARATOR + "" + NEW_LINE;

        return converted;
    }

    private Map _theMap;
    private Intcode _computer;
    private boolean _debug;
}