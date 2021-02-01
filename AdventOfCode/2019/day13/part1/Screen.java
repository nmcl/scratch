import java.util.*;

public class Screen
{
    public Screen (boolean debug)
    {
        _tiles = new Vector<Tile>();
        _debug = debug;
    }

    public final void updateTile (Tile theTile)
    {
        int index = _tiles.indexOf(theTile);

        if (index != -1)
            _tiles.set(index, theTile);
        else
            _tiles.add(theTile);
    }

    public final int numberOfBlocks ()
    {
        Enumeration<Tile> iter = _tiles.elements();
        int count = 0;

        while (iter.hasMoreElements())
        {
            Tile t = iter.nextElement();

            if (_debug)
                System.out.println("Checking tile: "+t);

            if (t.getId() == TileId.BLOCK)
                count++;
        }

        return count;
    }

    private Vector<Tile> _tiles;
    private boolean _debug;
}