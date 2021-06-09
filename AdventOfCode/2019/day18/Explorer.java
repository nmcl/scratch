/*
 * Go for all keys in sane direction until we hit a door that
 * we can't pass then change direction.
 *
 * BFS from current location to next key.
 */

public class Explorer
{
    public Explorer (Map theMap, boolean debug)
    {
        _theMap = theMap;
        _keys = new char[_theMap.numberOfKeys()];
        _keysFound = 0;
        _debug = debug;
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

    /*
     * Could encode the key set as a bitmask to make comparisons fast.
     * However, it's not critical at this stage.
     */

    private Map _theMap;
    private char[] _keys;
    private int _keysFound;
    private boolean _debug;
}