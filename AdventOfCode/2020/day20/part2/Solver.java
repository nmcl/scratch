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

        System.out.println("**NOW GOT\n"+tiles);

        Image theImage = createImage(tiles, tileTable);

        System.out.println("**HAVE\n"+theImage);

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

    private Image createImage (Vector<Tile> tiles, HashMap<Long, Integer> tileTable)
    {
        Tile topLeftCorner = null;
        Image theImage = new Image((int) Math.sqrt(tiles.size()), (int) Math.sqrt(tiles.size()), _debug);

        for (int i = 0; (i < tiles.size()) && (topLeftCorner == null); i++)
        {
            Tile t = tiles.elementAt(i);

            if (t.getNumberOfConnections() == 2) // has to be a corner!
            {
                if ((t.getConnections()[Tile.TOP] == 0) && (t.getConnections()[Tile.LEFT] == 0))
                    topLeftCorner = t;
            }
        }

        Tile current = topLeftCorner;
	    int x = 0;
	    int y = 0;

	    while (current.getConnections()[Tile.BOTTOM] != 0)
        {
	        Tile left = current;

	        while (current.getConnections()[Tile.RIGHT] != 0)
            {
                System.out.println("adding to "+y+" "+x);

                theImage.addTile(x, y, current);

                x++;

                current = tiles.get(tileTable.get(current.getConnections()[Tile.RIGHT]));

                if (current.getConnections()[Tile.RIGHT] == 0)
                {
                    System.out.println("adding to "+y+" "+x);
                    theImage.addTile(x, y, current);

                    y++;
                }
            }

            current = tiles.get(tileTable.get(left.getConnections()[Tile.BOTTOM]));

            if (current.getConnections()[Tile.BOTTOM] == 0)
            {
                while (current.getConnections()[Tile.RIGHT] != 0)
                {
                    System.out.println("adding to "+y+" "+x);
                    theImage.addTile(x, y, current);

                    x++;

                    current = tiles.get(tileTable.get(current.getConnections()[Tile.RIGHT]));

                    if (current.getConnections()[Tile.RIGHT] == 0)
                    {
                        System.out.println("adding to "+y+" "+x);
                        theImage.addTile(x, y, current);

                        y++;
                    }
                }
            }
        }

        return theImage;
    }

    private boolean _debug;
}