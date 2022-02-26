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
        int dimension = (int) Math.sqrt((double) tiles.size());

        if (_debug)
            System.out.println("Dimensions "+dimension+" by "+dimension);

        return toReturn;
    }

    private boolean _debug;
}