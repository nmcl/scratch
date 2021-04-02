import java.util.*;

public class Camera
{
    // ASCII codes.

    public static final int SCAFFOLDING_CODE = 35;
    public static final int OPEN_SPACE_CODE = 46;
    public static final int NEW_LINE = 10;

    public static final String INITIAL_INPUT = "";

    public Camera (Vector<String> values, boolean debug)
    {
        _computer = new Intcode(values, INITIAL_INPUT, debug);
        _debug = debug;
    }

    public final void takePicture ()
    {
        while (!_computer.hasHalted())
        {
            _theComputer.singleStepExecution();

            if (_theComputer.hasOutput())
            {
                String output = _theComputer.getOutput();

                System.out.println(output);
            }
        }
    }
    
    @Override
    public String toString ()
    {
        String str = "";

        return str;
    }

    private Intcode _computer;
    private boolean _debug;
}