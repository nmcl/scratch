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
        HashMap<Long, Integer> tileTable = new HashMap<Long, Integer>();
	
        for (int i = 0; i < tiles.size(); i++)
            tileTable.put(tiles.elementAt(i).getID(), i);

	    System.out.println("**GOT "+tileTable);

	    HashSet<Tile> visited = new HashSet<Tile>();
	    LinkedList<Tile> queue = new LinkedList<Tile>();
	
        queue.add(tiles.get(0));

	    while (!queue.isEmpty())
        {
            Tile current = queue.poll();
            
            visited.add(current);

            for (int j = 0; j < tiles.size(); j++)
            {
                Tile t = tiles.elementAt(j);

                if (!visited.contains(t) && !current.equals(t))
                {
                    if (connects(current, t))
                        queue.add(t);
                }
	        }
	    }

	    System.out.println("**NOW have "+visited);

        for (int i = 0; i < tiles.size(); i++)
        {
            Tile t = tiles.elementAt(i);

            for (int j = 0; j < tiles.size(); j++)
            {
                if (!t.equals(tiles.elementAt(j)))
                {
                    connects(t, tiles.elementAt(j));
                }
            }
        }

        return null;
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