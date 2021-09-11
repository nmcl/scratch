import java.util.*;
import java.io.*;

public class Grid
{
    public static final int DEFAULT_WIDTH = 5;
    public static final int DEFAULT_HEIGHT = 5;

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
        Tile[][] _nextWorld = new Tile[_height][_width];
        
        for (int i = 0; i < _height; i++)
        {
            for (int j = 0; j < _width; j++)
            {
                int adjacentBugs = 0;
                int emptySpaces = 0;

                // ignore range checking and rely on exception!

                if (_debug)
                    System.out.println("Checking tile < "+i+", "+j+" > : "+_theWorld[i][j]);

                try
                {
                    if (adjacentBug(i-1, j))
                        adjacentBugs++;
                    else
                        emptySpaces++;
                }
                catch (IndexOutOfBoundsException ex)
                {
                    emptySpaces++;
                }

                try
                {
                    if (adjacentBug(i+1, j))
                        adjacentBugs++;
                    else
                        emptySpaces++;
                }
                catch (IndexOutOfBoundsException ex)
                {
                    emptySpaces++;
                }

                try
                {
                    if (adjacentBug(i, j-1))
                        adjacentBugs++;
                    else
                        emptySpaces++;
                }
                catch (IndexOutOfBoundsException ex)
                {
                    emptySpaces++;
                }

                try
                {
                    if (adjacentBug(i, j+1))
                        adjacentBugs++;
                    else
                        emptySpaces++;
                }
                catch (IndexOutOfBoundsException ex)
                {
                    emptySpaces++;
                }

                if (_debug)
                {
                    System.out.println("Empty spaces: "+emptySpaces);
                    System.out.println("Adjacent bugs: "+adjacentBugs);
                }

                if (_theWorld[i][j].isBug())
                {
                    if (_debug)
                        System.out.println("Tile contains bug.");

                    if (adjacentBugs == 1)
                    {
                        if (_debug)
                            System.out.println("New tile will be bug.");

                        _nextWorld[i][j] = new Tile(TileId.BUG);
                    }
                    else
                    {
                        if (_debug)
                            System.out.println("New tile will be empyy.");

                        _nextWorld[i][j] = new Tile(TileId.EMPTY_SPACE);
                    }
                }
                else
                {
                    if (_debug)
                        System.out.println("Tile is empty.");

                    if ((adjacentBugs == 1) || (adjacentBugs == 2))
                    {
                        if (_debug)
                            System.out.println("New tile will be bug.");

                        _nextWorld[i][j] = new Tile(TileId.BUG);
                    }
                    else
                    {
                        if (_debug)
                            System.out.println("New tile will be empty.");

                        _nextWorld[i][j] = new Tile(TileId.EMPTY_SPACE);
                    }
                }
            }
        }

        _theWorld = _nextWorld;
    }

    @Override
    public String toString ()
    {
        String str = "";

        for (int i = 0; i < _height; i++)
        {
            for (int j = 0; j < _width; j++)
                str += _theWorld[i][j];
            
            str += "\n";
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

            if ((temp._height == _height) && (temp._width == _width))
            {
                for (int i = 0; i < _height; i++)
                {
                    for (int j = 0; j < _width; j++)
                    {
                        if (temp._theWorld[i][j].type() != _theWorld[i][j].type())
                            return false;
                    }
                }

                return true;
            }
        }

        return false;
    }

    private boolean adjacentBug (int i, int j) throws IndexOutOfBoundsException
    {
        if (_debug)
            System.out.println("Checking < "+i+", "+j+" >");

        if (_theWorld[i][j].isBug())
            return true;
        else
            return false;
    }

    private void loadWorld (String inputFile)
    {
        BufferedReader reader = null;
        int row = 0;

        _theWorld = new Tile[_height][_width];

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                for (int i = 0; i < line.length(); i++)
                {
                    if (TileId.valid(line.charAt(i)))
                        _theWorld[row][i] = new Tile(line.charAt(i));
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
    }

    private Tile[][] _theWorld;
    private int _height;
    private int _width;
    private boolean _debug;
}