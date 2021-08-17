import java.util.*;
import java.io.*;

public class Maze
{
    public static final String START = "AA";
    public static final String END = "ZZ";

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

    public final Vector<Wormhole> innerWormholes ()
    {
        return _innerWormholes;
    }

    public final Vector<Wormhole> outerWormholes ()
    {
        return _outerWormholes;
    }

    public boolean isPassage (Coordinate coord)
    {
        int x = coord.getX();
        int y = coord.getY();

        if ((x >= 0) && (y >= 0))
        {
            if ((x <= _width) && (y <= _height))
            {
                if (_theMaze[y][x].content() == TileId.PASSAGE)
                    return true;
            }
        }

        return false;
    }

    private final String createRepresentation (boolean ignorePortals)
    {
        String str = "Maze < minX: "+_minX+", maxX: "+_maxX+", minY: "+_minY+", maxY: "+_maxY+" >\n";

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
                        case TileId.SPACE:
                        {
                            map.add(new Tile(new Coordinate(i, _height), asChar[i]));
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
         * Convert to 2d array. Easier to manipulate.
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

        /*
         * Could merge this loop into the one above (or the case statement) but
         * it becomes harder to read (and debug!)
         */

        for (y = 3; y < _height -3; y++)
        {
            for (x = 3; x < _width -3; x++)
            {
                if (_theMaze[y][x].content() == TileId.SPACE)
                {
                    _minX = Math.min(_minX, x);
                    _maxX = Math.max(_maxX, x);
                    _minY = Math.min(_minY, y);
                    _maxY = Math.max(_maxY, y);
                }
            }
        }

        if (_debug)
            System.out.println("Maze < minX: "+_minX+", maxX: "+_maxX+", minY: "+_minY+", maxY: "+_maxY+" >\n");

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
        /*
         * We have inner portals/wormholes (inside the circle) and outer. Find them
         * and then hook them up.
         */

        _innerWormholes = new Vector<Wormhole>();

        for (int x = _minX; x <= _maxX -1; x++)
        {
            if ((_theMaze[_minY][x].content() == TileId.PORTAL) && (_theMaze[_minY +1][x].content() == TileId.PORTAL))
            {
                _innerWormholes.add(new Wormhole(_theMaze[_minY][x], _theMaze[_minY +1][x], new Coordinate(x, _minY -1)));
            }

            if ((_theMaze[_maxY -1][x].content() == TileId.PORTAL) && (_theMaze[_maxY][x].content() == TileId.PORTAL))
            {
                _innerWormholes.add(new Wormhole(_theMaze[_maxY -1][x], _theMaze[_maxY][x], new Coordinate(x, _maxY +1)));
            }
        }

        for (int y = _minY; y <= _maxY -1; y++)
        {
            if ((_theMaze[y][_minX].content() == TileId.PORTAL) && (_theMaze[y][_minY +1].content() == TileId.PORTAL))
            {
                _innerWormholes.add(new Wormhole(_theMaze[y][_minX], _theMaze[y][_minY +1], new Coordinate(_minX -1, y)));
            }

            if ((_theMaze[y][_maxX -1].content() == TileId.PORTAL) && (_theMaze[y][_maxX].content() == TileId.PORTAL))
            {
                _innerWormholes.add(new Wormhole(_theMaze[y][_maxX -1], _theMaze[y][_maxX], new Coordinate(_maxX +1, y)));
            }
        }

        // now the outer ones ...

        _outerWormholes = new Vector<Wormhole>();
    
        for (int x = 2; x < _width -2; x++)
        {
            if (_theMaze[0][x].content() == TileId.PORTAL)
                _outerWormholes.add(new Wormhole(_theMaze[0][x], _theMaze[1][x], new Coordinate(x, 2)));

            if (_theMaze[_height -2][x].content() == TileId.PORTAL)
                _outerWormholes.add(new Wormhole(_theMaze[_height -2][x], _theMaze[_height -1][x], new Coordinate(x, _height -3)));
        }

        for (int y = 2; y < _height -2; y++)
        {
            if (_theMaze[y][0].content() == TileId.PORTAL)
                _outerWormholes.add(new Wormhole(_theMaze[y][0], _theMaze[y][1], new Coordinate(2, y)));

            if (_theMaze[y][_width -2].content() == TileId.PORTAL)
                _outerWormholes.add(new Wormhole(_theMaze[y][_width -2], _theMaze[y][_width -1], new Coordinate(_width -3, y)));
        }
    }

    private Tile[][] _theMaze = null;
    private Vector<Wormhole> _innerWormholes = null;
    private Vector<Wormhole> _outerWormholes = null;
    private int _minX = Integer.MAX_VALUE;
    private int _maxX = 0;
    private int _minY = Integer.MAX_VALUE;
    private int _maxY = 0;
    private int _width = 0;
    private int _height = 0;
    private boolean _debug;
}