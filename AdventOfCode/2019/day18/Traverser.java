/*
 * We need to get all of the keys. We only need to open doors if they prevent
 * us from getting to a key.
 * 
 * - Order the keys and their locations first in the tunnels.
 * - The order of keys is always a to z.
 * - If we get to a door and we don't have the key to get through it then backtrack
 *   and find the key? Or don't even go there in the first place?
 * - If another key is closer to our current location than going through the
 *   next door then collect the key for shortest path.
 */

 // do a BF search?
 
public class Traverser
{
    public Traverser (Map theMap, boolean debug)
    {
        _theMap = theMap;
        _debug = debug;
    }

    private Map _theMap;
    private boolean _debug;
}