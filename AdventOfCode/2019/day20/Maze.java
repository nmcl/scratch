import java.util.*;
import java.io.*;

public class Maze
{
    public Maze (String data)
    {
        if (!loadData(data))
            System.out.println("Error in loading data file: "+data);
    }

    public String toString ()
    {

    }
    
    private final boolean loadData (String file)
    {
        BufferedReader reader = null;
        boolean valid = true;

        try
        {
            reader = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                char[] asChar = line.toCharArray();  // all lines are the same length
                
                if (_width == 0)
                    _width = asChar.length;

                for (int i = 0; i < _width; i++)
                {
                    switch (asChar[i])
                    {
                        case TileId.WALL:
                        case TileId.SPACE:
                        case TileId.PASSAGE:
                            _theMaze.add(new Tile(new Coordinate(i, _height), asChar[i]));
                        default:
                            _theMaze.add(new Tile(new Coordinate(i, _height), TileId.SPACE));
                    }
                }

                _height++;
            }
        }
        catch (Throwable ex)
        {
            valid = false;

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

        return valid;
    }

    private Vector<Tile> _theMaze;
    private int _height = 0;
    private int _width = 0;
}