import java.util.*;

public class Traveller
{
    public Traveller (Maze maze, boolean debug)
    {
        _theMaze = maze;
        _debug = debug;
    }

    public int findAllKeys (List<String> inputs)
    {
        Vector<Wormhole> outerWormholes = _theMaze.outerWormholes();
        Vector<Wormhole> innerWormholes = _theMaze.innerWormholes();
        Wormhole start = Util.findWormhole(outerWormholes, Maze.START);
        Wormhole end = Util.findWormhole(outerWormholes, Maze.END);
        
        return -1;
    }

    private HashMap<Coordinate, List<Route>> findRoutes (Vector<Wormhole> outerWormholes, Vector<Wormhole> innerWormholes)
    {
        HashMap<Coordinate, List<Route>> routesForEachCoordinate = new HashMap<Coordinate, List<Route>>();
        Set<Wormhole> outerSet = new HashSet<Wormhole>(outerWormholes);
        Iterator<Wormhole> iter = outerSet.iterator();

        while (iter.hasNext())
        {
            Wormhole toCheck = iter.next();
            int index = innerWormholes.indexOf(toCheck);

            if (index != -1)
            {
                Coordinate innerLocation = innerWormholes.elementAt(index).getLocation();
                List<Route> outerRoutes = routesForEachCoordinate.computeIfAbsent(toCheck.getLocation(), (k) -> new ArrayList<>());

                outerRoutes.add(new Route(toCheck.getLocation(), innerLocation));

                List<Route> innerRoutes = routesForEachCoordinate.computeIfAbsent(innerLocation, (k) -> new ArrayList<>());

                innerRoutes.add(new Route(innerLocation, toCheck.getLocation()));
            }
        }

        return routesForEachCoordinate;
    }

    private Maze _theMaze;
    private boolean _debug;
}