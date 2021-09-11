import java.util.*;
import java.io.*;

public class Grid
{
    public static final int DEFAULT_WIDTH = 5;
    public static final int DEFAULT_HEIGHT = 5;

    public Grid (String fileName)
    {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH, fileName);
    }

    public Grid (int height, int width, String fileName)
    {
        _height = height;
        _width = width;

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
}