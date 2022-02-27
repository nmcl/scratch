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
        int x = dimension / 2;
        int y = x;
        Tile[][] thePuzzle = new Tile[dimension][dimension];

        if (_debug)
            System.out.println("Dimensions "+dimension+" by "+dimension);

        System.out.println("Centre "+x+" "+y);
        
        for (int i = 0; i < tiles.size(); i++)
        {
            Tile t = tiles.elementAt(i);

            matchingTiles(t, tiles);
        }

        return toReturn;
    }

    private Vector<Tile> matchingTiles (Tile toCheck, Vector<Tile> tiles)
    {
        Vector<Tile> matches = new Vector<Tile>();

        for (int i = 0; i < tiles.size(); i++)
        {
            Tile t = tiles.elementAt(i);
            boolean matchedEdge = false;

            if (!t.equals(toCheck))  // don't compare with ourself!
            {
                for (int j = 0; (j < 4) && !matchedEdge; j++)
                {
                    for (int k = 0; k < 4; k++)
                    {
                        if (t.getEdges()[j].equals(toCheck.getEdges()[k]))
                        {
                            matchedEdge = true;
                            break;
                        }
                    }
                }

                if (matchedEdge)
                {
                    matches.add(t);

                    System.out.println("Tile "+t.getID()+" shares common edges with tile "+toCheck.getID());
                }
            }
        }

        System.out.println("Tile "+toCheck.getID()+" shares edges with "+matches.size()+" other tiles.");

        return matches;
    }

    private boolean _debug;
}