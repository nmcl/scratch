public class JoltageAdapter
{
    public JoltageAdapter (int voltage, boolean debug)
    {
        _joltage = voltage;
        _debug = debug;
    }

    public int outputJoltage ()
    {
        return _joltage;
    }

    @Override
    public String toString ()
    {
        return "Adapter joltage: "+_joltage;
    }

    private int _joltage;
    private boolean _debug;
}