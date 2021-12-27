public class Device
{
    public Device (JoltageAdapter builtin, boolean debug)
    {
        _builtin = builtin;
        _debug = debug;
    }

    public JoltageAdapter getAdapter ()
    {
        return _builtin;

    }
    private JoltageAdapter _builtin;
    private boolean _debug;
}