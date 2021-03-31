public class Camera
{
    public Camera (Vector<String> values, String initialInput, boolean debug)
    {
        _computer = new Intcode(values, initialInput, debug);
        _debug = debug;
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