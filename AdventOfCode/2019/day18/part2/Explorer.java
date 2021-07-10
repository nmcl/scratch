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
    private static final int PERIODICITY = 50000;  // manually worked out value

    public Explorer (Map theMap, boolean debug)
    {
        _theMap = theMap;
        _start = _theMap.getEntrance();
        _totalNumnberOfKeys = _theMap.numberOfKeys();
        _states = null;
        _allStates = null;
        _iter = 0;
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

    public int findAllKeys ()
    {
        _states = new ArrayDeque<State>();
        _allStates = new Vector<State>();
        _iter = 0;

        if (_debug)
        {
            System.out.println("Starting search at "+_start);
            System.out.println("totalKeys "+_totalNumnberOfKeys);
        }

        _states.offer(new State(_start));

        // for long running searches we should maybe print out the status periodically.

        while (_states.size() > 0)
        {
            State theState = _states.pop();
            
            _iter++;

            if (_iter % PERIODICITY == 0)
                System.out.println("Current state: "+theState);

            if (_debug)
                System.out.println("State keys "+theState.numberOfKeys());

            if (theState.numberOfKeys() == _totalNumnberOfKeys)
                return theState.numberOfSteps();

            for (Coordinate nextPosition : theState.getPosition().directions())
            {
                if (_debug)
                    System.out.println("nextPosition "+nextPosition);

                if (_theMap.validPosition(nextPosition))
                {
                    char content = _theMap.getContent(nextPosition);

                    if (Util.isDoor(content) && !theState.hasKey(content))
                    {
                        if (_debug)
                            System.out.println("Is a door and we don't have the key!");
                    }
                    else
                    {
                        State nextState = new State(theState, nextPosition, content);

                        if (_debug)
                            System.out.println("nextState "+nextState);

                        if (!_allStates.contains(nextState))
                        {
                            _allStates.add(nextState);
                            _states.add(nextState);
                        }
                    }
                }
                else
                {
                    if (_debug)
                        System.out.println(nextPosition+" is invalid");
                }
            }
        }
        
        if (_debug)
            System.out.println("No route found!!");

        return -1;
    }

    private Map _theMap;
    private Coordinate _start;
    private int _totalNumnberOfKeys;
    private ArrayDeque<State> _states;
    private Vector<State> _allStates;
    private int _iter;
    private boolean _debug;
}