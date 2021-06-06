public class Explorer
{
    public static final int MAXIMUM_KEYS = 26;

    public Explorer ()
    {
        _keys = new char[MAXIMUM_KEYS];
        _keysFound = 0;
    }

    @Override
    public String toString ()
    {
        if (_keysFound > 0)
        {
            String str = "Keys found: ";

            for (int i = 0; i < _keysFound; i++)
            {
                str += ", "+_keys[i];
            }

            return str;
        }
        else
            return "No keys found.";
    }

    private char[] _keys;
    private int _keysFound;
}