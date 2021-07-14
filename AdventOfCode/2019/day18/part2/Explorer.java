import java.util.*;
import java.util.stream.*;

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

        if (_theMap.getEntrances().size() != 4)
        {
            System.out.println("Error - wrong number of entrances: "+_theMap.getEntrances().size());

            return -1;
        }

        if (_debug)
        {
            //System.out.println("Starting search at "+_start);
            System.out.println("totalKeys "+_totalNumnberOfKeys);
        }
/*
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
        */
        if (_debug)
            System.out.println("No route found!!");

        return -1;
    }

    private ArrayList<HashMap<Route, Route>> pathsBetweenKeys ()
    {
        ArrayList<HashMap<Route, Route>> allPaths = new ArrayList<HashMap<Route, Route>>();
        Enumeration<Coordinate> iter1 = _theMap.getEntrances().elements();

        while (iter1.hasMoreElements())
        {
            Coordinate startCoord = iter1.nextElement();
            HashMap<Route, Route> paths = new HashMap<Route, Route>();
            Enumeration<Coordinate> iter2 = _theMap.getKeyLocations().elements();

            while (iter2.hasMoreElements())
            {
                Coordinate keyCoord = iter2.nextElement();

                shortestPath(startCoord, keyCoord).ifPresent(p -> paths.put(p, p));
            }

            List<Coordinate> keyLocationsPerRegion = paths.keySet().stream().map(p -> p.getEnd()).collect(Collectors.toList());

            for (Coordinate from : keyLocationsPerRegion)
            {
                for (Coordinate to: _theMap.getKeyLocations())
                {
                    if (Objects.equals(from, to)) continue;

                    shortestPath(from, to).ifPresent(p -> paths.put(p, p));
                }
            }

            allPaths.add(paths);
        }

        return allPaths;
    }

    private Optional<Route> shortestPath (Coordinate from, Coordinate to)
    {
        HashMap<Coordinate, Integer> stepsTaken = new HashMap<Coordinate, Integer>();
        HashMap<Coordinate, Coordinate> track = new HashMap<Coordinate, Coordinate>();
        PriorityQueue<Coordinate> coords = new PriorityQueue<Coordinate>((Comparator.comparingInt(pos -> cost(stepsTaken, pos, to))));

        stepsTaken.put(from, 0);
        coords.offer(from);

        while (coords.size() > 0)
        {
            Coordinate pos = coords.poll();
            int steps = stepsTaken.get(pos);

            if (pos.equals(to))
            {
                Coordinate coord = coords.poll();

                if (coord.equals(to))
                {
                    Route theRoute = new Route(from, to, steps, traverse(coord, track));

                    return Optional.of(theRoute);
                }
            }
            else
            {
                pos.directions().stream()
                        .filter(next -> _theMap.validPosition(next) && !stepsTaken.containsKey(next))
                        .forEach(next -> {
                            stepsTaken.put(next, steps + 1);
                            track.put(next, pos);
                            coords.offer(next);
                        });
                    }
        }

        return Optional.empty();
    }

    private Set<Character> traverse (Coordinate pos, HashMap<Coordinate, Coordinate> track)
    {
        HashSet<Character> requiredKeys = new HashSet<Character>();

        while (track.containsKey(pos))
        {
            pos = track.get(pos);

            char value = _theMap.getContent(pos);

            if (Util.isDoor(value))
                requiredKeys.add(value);
        }

        return requiredKeys;
    }

    private int cost (HashMap<Coordinate, Integer> stepsToLocation, Coordinate start, Coordinate destination)
    {
        return stepsToLocation.get(start) + start.distanceTo(destination);
    }

    private Map _theMap;
    private int _totalNumnberOfKeys;
    private ArrayDeque<State> _states;
    private Vector<State> _allStates;
    private int _iter;
    private boolean _debug;
}