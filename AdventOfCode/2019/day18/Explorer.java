public class Explorer
{
    public static final int MAXIMUM_KEYS = 26;

    public Explorer (Node start, boolean debug)
    {
        _start = start;
        _keys = new char[MAXIMUM_KEYS];
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

    private void backtrack ()
    {
        /*
         * Decrement the number of steps as we go
         * back on our path.
         */
    }

    // Encode the key set as a bitmask to make comparisons fast.
    // go for all keys in sane direction until we hit a door that
    // we can't pass then change direction.
    // BFS from current location to next key.
    
    private Node _start;
    private char[] _keys;
    private int _keysFound;
    private boolean _debug;
}