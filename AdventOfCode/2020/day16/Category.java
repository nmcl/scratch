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

    @Override
    public String toString ()
    {
        return "Category "+_name;
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
            Category temp = (Category) obj;

            return _name.equals(temp._name);
        }

        return false;
    }

    private String _name;
    private boolean _debug;
}