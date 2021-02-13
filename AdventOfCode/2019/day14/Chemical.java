public class Chemical
{
    // these are special types.

    public static final String ORE = "ORE";
    public static final String FUEL = "FUEL";

    public Chemical (String name)
    {
        _name = name.trim();
    }

    public final String getName ()
    {
        return _name;
    }

    public final boolean isOre ()
    {
        if (_name.equals(Chemical.ORE))
            return true;
        else
            return false;
    }

    public final boolean isFuel ()
    {
        if (_name.equals(Chemical.FUEL))
            return true;
        else
            return false;
    }

    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

    @Override
    public String toString ()
    {
        return _name;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (obj instanceof Chemical)
        {
            Chemical temp = (Chemical) obj;

            return (temp._name.equals(_name));
        }

        return false;
    }

    private String _name;
}