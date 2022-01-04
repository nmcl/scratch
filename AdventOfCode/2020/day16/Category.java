public class Category
{
    public Category (String type, boolean debug)
    {
        _name = type;
        _debug = debug;
    }

    public boolean valid (int value)
    {
        return false;
    }

    public final String getType ()
    {
        return _name;
    }

    private String _name;
    private boolean _debug;
}