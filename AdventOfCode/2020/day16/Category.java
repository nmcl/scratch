public class Category
{
    public Category (String type, int r1, int r2, int r3, int r4, boolean debug)
    {
        _name = type;
        _r1 = r1;
        _r2 = r2;
        _r3 = r3;
        _r4 = r4;
        _debug = debug;
    }

    public boolean valid (Ticket t)
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
        return "Category "+_name+": "+_r1+"-"+_r2+" or "+_r3+"-"+_r4;
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
    private int _r1, _r2;
    private int _r3, _r4;
    private boolean _debug;
}