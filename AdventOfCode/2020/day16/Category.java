public class Category
{
    public Category (String type, boolean debug)
    {
        _name = type;
        _debug = debug;
    }

    public final String getType ()
    {
        return _name;
    }

    private String _name;
    private boolean _debug;
}