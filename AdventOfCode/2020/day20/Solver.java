import java.util.*;

public class Solver
{
    public Solver (boolean debug)
    {
        _debug = debug;
    }

    public Vector<Tile> arrangement (Vector<Tile> tiles)
    {
        Vector<Tile> toReturn = new Vector<Tile>();
        int dimension = (int) Math.sqrt((double) tiles.size());

        if (_debug)
            System.out.println("Dimensions "+dimension+" by "+dimension);

        for (int i = 0; i < tiles.size(); i++)
        {
            Tile t = tiles.elementAt(i);
        }

        return toReturn;
    }

    private int numberOfMatchingEdges (Tile toCheck, Vector<Tile> tiles)
    {
        int number = 0;
        
        for (int i = 0; i < tiles.size(); i++)
        {
            Tile t = tiles.elementAt(i);

            if (!t.equals(toCheck))
            {
                for (int j = 0; j < 4; j++)
                {

                }
            }
        }

        return number;
    }

    private boolean _debug;
}