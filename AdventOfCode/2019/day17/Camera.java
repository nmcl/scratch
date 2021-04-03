import java.util.*;

public class Camera
{
    private static final String INITIAL_INPUT = "";

    public Camera (Vector<String> values, boolean debug)
    {
        _theComputer = new Intcode(values, INITIAL_INPUT, debug);
        _scaffolding = new Scaffold(debug);
        _debug = debug;
    }

    public final void takePicture ()
    {
        while (!_theComputer.hasHalted())
        {
            _theComputer.singleStepExecution();

            if (_theComputer.hasOutput())
            {
                String output = _theComputer.getOutput();

                _scaffolding.addData(output);
            }
        }
    }

    public final void scanForIntersections ()
    {
        _scaffolding.scanForIntersections();
    }
    
    @Override
    public String toString ()
    {
        return _scaffolding.toString();
    }

    private Intcode _theComputer;
    private Scaffold _scaffolding;
    private boolean _debug;
}