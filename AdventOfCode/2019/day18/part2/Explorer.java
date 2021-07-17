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
        _iter = 0;

        if (_theMap.getEntrances().size() != Util.TOTAL_NUMBER_OF_ROBOTS)
        {
            System.out.println("Error - wrong number of entrances: "+_theMap.getEntrances().size());

            return -1;
        }

        if (_debug)
        {
            Vector<Coordinate> startingLocations = _theMap.getEntrances();
            
            for (int i = 0; i < Util.TOTAL_NUMBER_OF_ROBOTS; i++)
            {
                System.out.println("Robot "+i+" starting search at "+startingLocations.get(i));
            }

            System.out.println("totalKeys "+_totalNumnberOfKeys);
        }

        ArrayList<HashMap<Route, Route>> realmPaths = pathsBetweenKeys();
        List<List<Coordinate>> keyLocationsPerRealms = keysForRealm(realmPaths);
        PriorityQueue<Journey> journeys = new PriorityQueue<Journey>(Comparator.comparingInt(r -> r.getSteps()));
        HashMap<String, Integer> minimumSteps = new HashMap<String, Integer>();

        journeys.offer(new Journey(_theMap.getEntrances()));

        while (journeys.size() > 0)
        {
            Journey currentJourney = journeys.poll();

            _iter++;

            if (_iter % PERIODICITY == 0)
                System.out.println("Processing: "+currentJourney);

            if (currentJourney.foundKeys(_theMap.numberOfKeys()))
                return currentJourney.getSteps();

            for (int robotId = 0; robotId < Util.TOTAL_NUMBER_OF_ROBOTS; robotId++)
            {
                Coordinate robotLocation = currentJourney.getRobotLocation(robotId);
                HashMap<Route, Route> pathsForRobot = realmPaths.get(robotId);

                for (Coordinate nextCoord : keyLocationsPerRealms.get(robotId))
                {
                    if (validMove(currentJourney, currentJourney.getRobotLocation(robotId), nextCoord, pathsForRobot))
                    {
                        Journey theNextJourney = currentJourney.nextJourney(robotId, nextCoord, pathsForRobot, _theMap);

                        if (theNextJourney.getSteps() < minimumSteps.getOrDefault(theNextJourney.getId(), Integer.MAX_VALUE))
                        {
                            minimumSteps.put(theNextJourney.getId(), theNextJourney.getSteps());

                            journeys.offer(theNextJourney);
                        }
                    }
                }
            }
        }

        if (_debug)
            System.out.println("No route found!!");

        return -1;
    }

    private boolean validMove (Journey theJourney, Coordinate from, Coordinate next, HashMap<Route, Route> paths)
    {
        Route path = paths.get(new Route(from, next));

        if (path != null)
        {
            Character value = _theMap.getContent(next);

            if (!theJourney.hasKey(value))
            {
                for (char door: path.getDoors())
                {
                    if (!theJourney.hasKey(Util.keyForDoor(door)))
                        return false;
                }

                return true;
            }
        }

        return false;
    }

    private List<List<Coordinate>> keysForRealm (List<HashMap<Route, Route>> realmPaths)
    {
        return realmPaths.stream()
                .map(paths ->
                        paths.keySet().stream()
                                .map(p -> p.getEnd())
                                .collect(Collectors.toList())).collect(Collectors.toList());
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
        PriorityQueue<Coordinate> coords = new PriorityQueue<Coordinate>((Comparator.comparingInt(pos -> Util.cost(stepsTaken, pos, to))));

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

    private Map _theMap;
    private int _totalNumnberOfKeys;
    private int _iter;
    private boolean _debug;
}