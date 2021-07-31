import java.util.*;
import java.io.*;

public class Maze
{
    public Maze (String data, boolean debug)
    {
        _theMaze = new Vector<Tile>();
        _thePortals = new Vector<Portal>();

        if (!loadData(data))
            System.out.println("Error in loading data file: "+data);

        _debug = debug;
    }

    public String printWithPortals ()
    {
        return createRepresentation(false);
    }

    public String toString ()
    {
        return createRepresentation(true);
    }

    private String createRepresentation (boolean ignorePortals)
    {
        Enumeration<Tile> iter = _theMaze.elements();
        String str = "Maze < "+_width+", "+_height+" >\n";
        int y = 0;

        while (iter.hasMoreElements())
        {
            Tile theEntry = iter.nextElement();

            /*
             * Print without portals?
             */

            if (ignorePortals)
            {
                if (TileId.PORTAL != theEntry.content())
                    str += theEntry.toString();
                else
                    str += TileId.SPACE;
            }
            else
            {
                if (TileId.PORTAL == theEntry.content())
                {
                    Portal p = (Portal) theEntry;

                    str += p.getId();
                }
                else
                    str += theEntry.toString();
            }

            y++;

            if (y == _width)
            {
                y = 0;
                str += "\n";
            }
        }

        return str;
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
                            break;
                        default:
                            _theMaze.add(new Portal(new Coordinate(i, _height), asChar[i]));
                            break;
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

        if (valid)
            reparsePortals();

        return valid;
    }

    private void reparsePortals ()
    {

    }

    private Vector<Tile> _theMaze;
    private Vector<Portal> _thePortals;
    private int _height = 0;
    private int _width = 0;
    private boolean _debug;
}