public class Chemical
{
    public Chemical (String name)
    {
        _name = name.trim();
    }

    public final String getName ()
    {
        return _name;
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