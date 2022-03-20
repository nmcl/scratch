import java.util.*;

public class Solver
{
    public Solver (boolean debug)
    {
        _debug = debug;
    }

    /**
     * Loop through the tiles and find out how many of them have matching edges. Then,
     * based upon the number of shared edges we do the following:
     * 
     * 0: ERROR!!
     * 1: If a tile has only one matching edge, then it's easy to say where it needs
     * to be placed.
     * 2: Work outwards from the centre.
     */

    public Vector<Tile> solve (Vector<Tile> tiles)
    {
        Vector<Tile> cornerTiles = new Vector<Tile>();

        return cornerTiles;
    } 

    private boolean connects (Tile theTile, Tile toCheck)
    {
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
        }

        return false;
    }

    private boolean _debug;
}