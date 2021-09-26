import java.util.*;
import java.io.*;

/*
 * Infinite plane.
 */

public class Grid
{
    public static final int DEFAULT_WIDTH = 5;
    public static final int DEFAULT_HEIGHT = 5;
    public static final int DEFAULT_LEVELS = 5;

    public Grid (String fileName, boolean debug)
    {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH, fileName, debug);
    }

    public Grid (int height, int width, String fileName, boolean debug)
    {
        _theWorld = new Level[DEFAULT_LEVELS*2 +1];
        _height = height;
        _width = width;
        _debug = debug;

        int layer = -DEFAULT_LEVELS;

        for (int i = 0; i < DEFAULT_LEVELS*2 +1; i++)
        {
            _theWorld[i] = new Level(layer, _height, _width, _debug);

            layer++;
        }

        loadWorld(fileName);
    }

    /**
     * Each minute, The bugs live and die based on the number of bugs in the four adjacent tiles:
     *
     * - A bug dies (becoming an empty space) unless there is exactly one bug adjacent to it.
     * - An empty space becomes infested with a bug if exactly one or two bugs are adjacent to it.
     * 
     * Otherwise, a bug or empty space remains the same. (Tiles on the edges of the grid have fewer than
     * four adjacent tiles; the missing tiles count as empty space.) This process happens in every location
     * simultaneously; that is, within the same minute, the number of adjacent bugs is counted for every tile
     * first, and then the tiles are updated.
     * 
     * For the infinite plane ...
     * 
     * Tile 19 has four adjacent tiles: 14, 18, 20, and 24.
     * Tile G has four adjacent tiles: B, F, H, and L.
     * Tile D has four adjacent tiles: 8, C, E, and I.
     * Tile E has four adjacent tiles: 8, D, 14, and J.
     * Tile 14 has eight adjacent tiles: 9, E, J, O, T, Y, 15, and 19.
     * Tile N has eight adjacent tiles: I, O, S, and five tiles within the sub-grid marked ?.
     */

    public void evolve ()
    {
    }

    @Override
    public String toString ()
    {
        String str = "";

        for (int i = 0; i < _theWorld.length; i++)
        {
            str += "\nDepth "+_theWorld[i].getLevel()+":\n";
            str += _theWorld[i];
        }

        return str;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;
        
        if (getClass() == obj.getClass())
        {
            Grid temp = (Grid) obj;

            if (_theWorld.length == temp._theWorld.length)
            {
                boolean same = true;

                for (int i = 0; (i < _theWorld.length) && same; i++)
                {
                    if (!_theWorld[i].equals(temp._theWorld[i]))
                        same = false;
                }

                return same;
            }
        }

        return false;
    }

    private void loadWorld (String inputFile)
    {
        BufferedReader reader = null;
        int h = 0;

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                for (int i = 0; i < line.length(); i++)
                {
                    if (TileId.BUG == line.charAt(i))
                        _theWorld[DEFAULT_LEVELS].addBug(new ThreeDPoint(h, i, 0));
                }

                h++;
            }
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (Throwable ex)
            {
            }
        }
    }

    private Level[] _theWorld;
    private int _height;
    private int _width;
    private boolean _debug;
}