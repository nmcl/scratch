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
            }

            row++;
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