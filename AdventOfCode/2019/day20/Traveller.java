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
        Wormhole start = findWormhole(outerWormholes, Maze.START);
        Wormhole end = findWormhole(outerWormholes, Maze.END);
        
        return -1;
    }

    private Wormhole findWormhole (Vector<Wormhole> wormholes, String name)
    {
        Enumeration<Wormhole> iter = wormholes.elements();

        while (iter.hasMoreElements())
        {
            Wormhole hole = iter.nextElement();

            if (hole.getName().equals(name))
                return hole;
        }

        return null;
    }

    private Maze _theMaze;
    private boolean _debug;
}