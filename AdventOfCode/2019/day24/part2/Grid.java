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
     */

    public void evolve ()
    {
    }

    public Grid snapshot ()
    {
        return new Grid(this);
    }

    @Override
    public String toString ()
    {
        String str = "";

        for (int i = 0; i < _levels.length; i++)
        {
            str += "Level "+_levels[i].getLevel()+"\n";
            str += _levels[i];
            str += "----------\n";
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
        int row = 0;
        int level = 0 - DEFAULT_LEVELS;

        _levels = new Level[DEFAULT_LEVELS*2 +1];

        for (int i = 0; i < _levels.length; i++)
        {
            _levels[i] = new Level(_height, _width, level, _debug);

            level++;
        }

        Tile[][] levelZero = new Tile[_height][_width];

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                for (int i = 0; i < line.length(); i++)
                {
                    if (TileId.valid(line.charAt(i)))
                        levelZero[row][i] = new Tile(line.charAt(i));
                    else
                        System.out.println("Invalid world entry: "+line.charAt(i));
                }

                row++;
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

    private Level[] _levels;
    private int _height;
    private int _width;
    private boolean _debug;
}