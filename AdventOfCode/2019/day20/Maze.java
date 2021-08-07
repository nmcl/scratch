import java.util.*;
import java.io.*;

public class Maze
{
    public Maze (String data, boolean debug)
    {
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
        String str = "Maze < "+_minX+", "+_maxX+", "+_minY+", "+_maxY+" >\n";

        for (int y = 0; y < _height; y++)
        {
            for (int x = 0; x < _width; x++)
            {
                Tile theEntry = _theMaze[y][x];

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
            }

            str += "\n";
        }

        return str;
    }
    
    private final boolean loadData (String file)
    {
        BufferedReader reader = null;
        boolean valid = true;
        Vector<Tile> map = new Vector<Tile>();

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
                        case TileId.PASSAGE:
                        {
                            map.add(new Tile(new Coordinate(i, _height), asChar[i]));
                        }
                        break;
                        case TileId.SPACE:
                        {
                            map.add(new Tile(new Coordinate(i, _height), asChar[i]));

                            _minX = Math.min(_minX, i);
                            _maxX = Math.max(_maxX, i);
                            _minY = Math.min(_minY, _height);
                            _maxY = Math.max(_maxY, _height);
                        }
                        break;
                        default:  // add to Portal list
                        {
                            // have portal at the PASSAGE and have that instance print it?

                            Portal p = new Portal(new Coordinate(i, _height), asChar[i]);

                            map.add(p);
                        }
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
        
        /*
         * Convert to 2d array.
         */

        _theMaze = new Tile[_height][_width];

        Enumeration<Tile> iter = map.elements();
        int x = 0;
        int y = 0;

        while (iter.hasMoreElements())
        {
            _theMaze[y][x] = iter.nextElement();

            if (_debug)
                System.out.println("Assigning "+y+" "+x+" "+_theMaze[y][x]);

            x++;
            
            if (x >= _width)
            {
                x = 0;
                y++;
            }
        }

        if (valid)
            reparsePortals();

        return valid;
    }

    /*
     * We have two Portal instances per real Portal. Need to create one
     * instance.
     * 
     * To find the Portal, seach for the first letter (or any letter) and then
     * use the Coordinate of its location to look at the 4 squares around it.
     * Assume there are always 3 spaces around the first letter in the Portal
     * name. Assume there are always 2 spaces around the second letter, then
     * the first letter and a passage which represents the real location of the
     * Portal.
     *
     * Based upon the algortihm, we can then find the full Portal name and the Passage
     * to which it is tied.
     */

    private void reparsePortals ()
    {
        _innerWormholes = new Vector<Wormhole>();

        for (int x = _minX; x <= _maxX -1; x++)
        {
            if ((_theMaze[_minY][x].content() == TileId.PORTAL) && (_theMaze[_minY +1][x].content() == TileId.PORTAL))
            {
                _innerWormholes.add(new Wormhole(((Portal) _theMaze[_minY][x]).getId(), ((Portal) _theMaze[_minY +1][x]).getId(), new Coordinate(x, _minY -1)));
            }

            if ((_theMaze[_maxY -1][x].content() == TileId.PORTAL) && (_theMaze[_maxY][x].content() == TileId.PORTAL))
            {
                _innerWormholes.add(new Wormhole(((Portal) _theMaze[_maxY -1][x]).getId(), ((Portal) _theMaze[_maxY][x]).getId(), new Coordinate(x, _maxY +1)));
            }
        }

        for (int y = _minY; y <= _maxY -1; y++)
        {
            if ((_theMaze[y][_minX].content() == TileId.PORTAL) && (_theMaze[y][_minY +1].content() == TileId.PORTAL))
            {
                _innerWormholes.add(new Wormhole(((Portal) _theMaze[y][_minX]).getId(), ((Portal) _theMaze[y][_minY +1]).getId(), new Coordinate(_minX -1, y)));
            }

            if ((_theMaze[y][_maxX -1].content() == TileId.PORTAL) && (_theMaze[y][_maxX].content() == TileId.PORTAL))
            {
                _innerWormholes.add(new Wormhole(((Portal) _theMaze[y][_maxX -1]).getId(), ((Portal) _theMaze[y][_maxX]).getId(), new Coordinate(_maxX +1, y)));
            }
        }
    }

    private Tile[][] _theMaze = null;
    private Vector<Wormhole> _innerWormholes = null;
    private int _minX = Integer.MAX_VALUE;
    private int _maxX = 0;
    private int _minY = Integer.MAX_VALUE;
    private int _maxY = 0;
    private int _width = 0;
    private int _height = 0;
    private boolean _debug;
}