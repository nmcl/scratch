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
    public boolean equals (Object obj)
    {
        if (obj == null)
        return false;

        if (this == obj)
            return true;
        
        if (getClass() == obj.getClass())
        {
            JoltageAdapter temp = (JoltageAdapter) obj;

            return (temp._joltage == _joltage);
        }

        return false;
    }

    @Override
    public String toString ()
    {
        return "Adapter joltage: "+_joltage;
    }

    private int _joltage;
    private boolean _debug;
}