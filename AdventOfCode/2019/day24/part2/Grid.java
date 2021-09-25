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

    public static final int CENTRE_HEIGHT = 2;  // TODO only makes sense for 5x5 so need to fix this!!
    public static final int CENTRE_WIDTH = 2;

    public Grid (String fileName, boolean debug)
    {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH, fileName, debug);
    }

    public Grid (int height, int width, String fileName, boolean debug)
    {
        _theWorld = new HashSet<ThreeDPoint>();
        _height = height;
        _width = width;
        _debug = debug;

        loadWorld(fileName);
    }

    public final int getLevels ()
    {
        return _levels.length;
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
        Level[] nextIteration = new Level[_levels.length];

        for (int i = 0; i < _levels.length; i++)
        {
            Level below = ((i > 0) ? _levels[i-1] : new Level(_height, _width, i-1, _debug));
            Level above = ((i < _levels.length -1) ? _levels[i+1] : new Level(_height, _width, i+1, _debug));

            for (int h = 0; h < _height; h++)
            {
                for (int w = 0; w < _width; w++)
                {
                    int adjacentBugs = 0;
                    int emptySpaces = 0;

                    if (!_levels[i].nestedLevel(h, w))
                    {
                        try
                        {
                            if (_levels[i].containsBug(h, w))
                                adjacentBugs++;
                            else
                                emptySpaces++;
                        }
                        catch (IndexOutOfBoundsException e)
                        {
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString ()
    {
        String str = "";

        for (int i = 0; i < _levels.length; i++)
        {
            str += "\nDepth "+_levels[i].getLevel()+":\n";
            str += _levels[i];
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

            if (_levels.length == temp._levels.length)
            {
                boolean same = true;

                for (int i = 0; (i < _levels.length) && same; i++)
                {
                    if (!_levels[i].equals(temp._levels[i]))
                        same = false;
                }

                return same;
            }
        }

        return false;
    }

    protected Grid (Grid theGrid)
    {
        _height = theGrid._height;
        _width = theGrid._width;
        _debug = theGrid._debug;
        _levels = new Level[theGrid._levels.length];

        for (int i = 0; i < _levels.length; i++)
        {
            _levels[i] = new Level(theGrid._levels[i]);
        }
    }

    private void loadWorld (String inputFile)
    {
        BufferedReader reader = null;

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                for (int i = 0; i < line.length(); i++)
                {
                    if (TileId.BUG == line.charAt(i))
                        _theWorld
                }
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

        _levels[DEFAULT_LEVELS] = new Level(levelZero, 0, _debug);
    }

    private HashSet<ThreeDPoint> _theWorld;
    private int _height;
    private int _width;
    private boolean _debug;
}