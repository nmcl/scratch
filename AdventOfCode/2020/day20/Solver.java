import java.util.*;

public class Solver
{
    public Solver (boolean debug)
    {
        _debug = debug;
    }

    Vector<Tile> arrangement (Vector<Tile> tiles)
    {
        Vector<Tile> toReturn = new Vector<Tile>();
        double dimension = Math.sqrt((double) tiles.size());

        if (_debug)
            System.out.println("Dimensions "+dimension+" by "+dimension);

        return toReturn;
    }

    private boolean _debug;
}