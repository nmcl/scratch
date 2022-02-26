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

            numberOfMatchingEdges(t, tiles);
        }

        return toReturn;
    }

    private int numberOfMatchingEdges (Tile toCheck, Vector<Tile> tiles)
    {
        for (int i = 0; i < tiles.size(); i++)
        {
            Tile t = tiles.elementAt(i);
            int number = 0;

            if (!t.equals(toCheck))  // don't compare with ourself!
            {
                for (int j = 0; j < 4; j++)
                {
                    for (int k = 0; k < 4; k++)
                    {
                        if (t.getEdges()[j].equals(toCheck.getEdges()[k]))
                            number++;
                    }
                }

                System.out.println("Tile "+t.getID()+" shares "+number+" common edges with tile "+toCheck.getID());
            }
        }

        return 0;
    }

    private boolean _debug;
}