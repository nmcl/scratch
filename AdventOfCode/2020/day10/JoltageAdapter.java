public class JoltageAdapter
{
    public JoltageAdapter (int voltage, boolean debug)
    {
        _joltage = voltage;
        _debug = debug;
    }

    private int outputJoltage ()
    {
        return _joltage;
    }

    private int _joltage;
    private boolean _debug;
}