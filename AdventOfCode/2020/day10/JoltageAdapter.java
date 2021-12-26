public class JoltageAdapter
{
    public JoltageAdapter (boolean debug)
    {
        _debug = debug;
    }

    private int outputJoltage ()
    {
        return _joltage;
    }

    private int _joltage;
    private boolean _debug;
}