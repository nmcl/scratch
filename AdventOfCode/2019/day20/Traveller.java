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

    private Maze _theMaze;
    private boolean _debug;
}