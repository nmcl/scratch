import java.util.*;

/**
 * Loop through the tiles and find out how many of them have matching edges. Then,
 * based upon the number of shared edges we do the following:
 * 
 * 0: ERROR!!
 * 1: If a tile has only one matching edge, then it's easy to say where it needs
 * to be placed.
 * 2: Work outwards from the centre.
 */

public class Solver
{
    public Solver (boolean debug)
    {
        _debug = debug;
    }

    /*
     * This time we need to solve the image and return the entire
     * thing assembled, i.e., not just the corners.
     */

    public Vector<Tile> solve (Vector<Tile> tiles)
    {
        Vector<Tile> cornerTiles = new Vector<Tile>();

        for (int i = 0; i < tiles.size(); i++)
        {
            Tile current = tiles.elementAt(i);
            int count = 0;

            if (_debug)
                System.out.println("Tile\n"+current.getID());

            for (int j = 0; j < tiles.size(); j++)
            {
                if (!current.equals(tiles.elementAt(j)) && connects(current, tiles.elementAt(j)))
                {
                    count++;
                }
            }

            if (_debug)
                System.out.println("Final count is "+count);

            if (count == 2)
                cornerTiles.add(current);
        }

        // once we have number of matching sides we can assemble the picture.
        
        return cornerTiles;
    } 

    private boolean connects (Tile theTile, Tile toCheck)
    {
        if (_debug)
            System.out.println("Checking "+toCheck.getID()+" against "+theTile.getID());
        
        for (int i = 0; i < 4; i++)
        {
            if (theTile.connectBottomToTop(toCheck) || theTile.connectTopToBottom(toCheck) ||
                theTile.connectLeftToRight(toCheck) || theTile.connectRightToLeft(toCheck))
            {
                return true;
            }

            if (!toCheck.isFrozen())
                toCheck.rotate();
        }
        
        if (!toCheck.isFrozen())
            toCheck.invert();

        for (int i = 0; i < 4; i++)
        {
            if (theTile.connectBottomToTop(toCheck) || theTile.connectTopToBottom(toCheck) ||
                theTile.connectLeftToRight(toCheck) || theTile.connectRightToLeft(toCheck))
            {
                return true;
            }

            if (!toCheck.isFrozen())
                toCheck.rotate();
        }

        return false;
    }

    private boolean _debug;
}