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
    private static long SEPARATOR_ASCII = 44;
    private static String NEW_LINE_ASCII = "10";

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
        long[] converted = convertToAscii(sequence);

        _computer.addInput(IntcodeUtil.convert(converted));
        _computer.setInput(NEW_LINE_ASCII);
    }

    public void setFunctions (String funcA, String funcB, String funcC)
    {
        long[] funcAC = convertToAscii(funcA);
        long[] funcBC = convertToAscii(funcB);
        long[] funcCC = convertToAscii(funcC);

        _computer.addInput(IntcodeUtil.convert(funcAC));
        _computer.setInput(NEW_LINE_ASCII);

        _computer.addInput(IntcodeUtil.convert(funcBC));
        _computer.setInput(NEW_LINE_ASCII);

        _computer.addInput(IntcodeUtil.convert(funcCC));
        _computer.setInput(NEW_LINE_ASCII);
    }

    public void setContinuousVideo (boolean video)
    {
        if (video)
            _computer.setInput("y");
        else
            _computer.setInput("n");

        _computer.setInput(NEW_LINE_ASCII);
    }

    public long sweep ()
    {
        _computer.executeProgram();

        /*
         * The amount of dust collected should be the last output
         * from the computer.
         */

        String dustCollected = null;

        while (_computer.hasOutput())
        {
            dustCollected = _computer.getOutput();

            System.out.println("output "+dustCollected);
        }

        return Long.parseLong(dustCollected);
    }

    private long[] convertToAscii (String sequence)
    {
        long[] converted = new long[sequence.length()];
        char[] asArray = sequence.toCharArray();

        for (int i = 0; i < asArray.length; i++)
        {
            if (asArray[i] != SEPARATOR)
                converted[i] = (long) asArray[i];
            else
                converted[i] = SEPARATOR_ASCII;
        }

        return converted;
    }

    private Map _theMap;
    private Intcode _computer;
    private boolean _debug;
}