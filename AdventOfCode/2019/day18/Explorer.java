import java.util.*;

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
        _start = _theMap.getEntrance();
        _totalNumnberOfKeys = _theMap.numberOfKeys();
        _states = new ArrayDeque<State>();
        _visited = new Vector<State>();
        _debug = debug;
    }

   /*
    * Algorithm:
    *
    * - initial starting point @
    * - move to find next key
    * - if we hit a door ...
    *      if we have the key, keep going
    *      if we do not have the key, we can't get through the door so we
    *      need to stop going in that direction and find the key.
    * - each time we find a key, reset the starting point and remember
    *   to reset the visited bit in each Node.
    * - keep going until we hit a door or find all keys
    */

    private Map _theMap;
    private Coordinate _start;
    private int _totalNumnberOfKeys;
    private ArrayDeque<State> _states;
    private Vector<State> _visited;
    private boolean _debug;
}