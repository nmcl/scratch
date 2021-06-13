/*
 * We need to get all of the keys. We only need to open doors if they prevent
 * us from getting to a key.
 * 
 * - Order the keys and their locations first in the tunnels?
 * - The order of keys is always a to z? Not necessarily.
 * - If we get to a door and we don't have the key to get through it then backtrack
 *   and find the key? Or don't even go there in the first place?
 * - If another key is closer to our current location than going through the
 *   next door then collect the key for shortest path.
 * - Go for all keys in sae direction until we hit a door that
 *   we can't pass then change direction.
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

    /*
     * May insert Maze mapper from previous Days. But for now
     * let's see what a BF search produces.
     */

    public void moveAroundMap ()  // May move this to a test class (derived from Explorer)
    {

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