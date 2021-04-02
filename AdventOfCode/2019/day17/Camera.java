import java.util.*;

public class Camera
{
    // ASCII codes.
    
    public static final int SCAFFOLDING_CODE = 35;
    public static final int OPEN_SPACE_CODE = 46;
    public static final int NEW_LINE = 10;

    public Camera (Vector<String> values, String initialInput, boolean debug)
    {
        _computer = new Intcode(values, initialInput, debug);
        _debug = debug;
    }

    public final void takePicture ()
    {

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