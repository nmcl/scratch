import java.util.*;

/**
 * A map of the tunnel system.
 */

public class Map
{
    public Map (Vector<String> input, boolean debug)
    {
        _theMap = new Vector<Cell>();
        _keys = new Vector<Character>();
        _doors = new Vector<Character>();
        _debug = debug;

        createMap(input);
    }

    public Coordinate getEntrance ()
    {
        return _entrance;
    }

    public int numberOfDoors ()
    {
        return _doors.size();
    }

    public int numberOfKeys ()
    {
        return _keys.size();
    }

    @Override
    public String toString ()
    {
        Enumeration<Cell> iter = _theMap.elements();
        int index = 1;
        String str = "Map <"+_maxX+", "+_maxY+">:\n\n";

        while (iter.hasMoreElements())
        {
            str += iter.nextElement().getContents();

            if (index++ == _maxX)
            {
                str += "\n";

                index = 1;
            }
        }

        return str;
    }

    // create the map from input
    
    private void createMap (Vector<String> input)
    {
        Enumeration<String> iter = input.elements();
        int y = -1;

        _maxX = input.get(0).length(); // all lines are the same length (check?)

        while (iter.hasMoreElements())
        {
            y++;

            String line = iter.nextElement();

            for (int x = 0; x < _maxX; x++)
            {
                Cell theCell = new Cell(new Coordinate(x, y), line.charAt(x), _debug);

                if (theCell.isEntrance())
                    _entrance = theCell.position();  // should be only one!
                else
                {
                    if (theCell.isKey())
                        _keys.add(theCell.getContents());
                    else
                    {
                        if (theCell.isDoor())
                            _doors.add(theCell.getContents());
                    }
                }

                if (_debug)
                    System.out.println(theCell);

                _theMap.add(theCell);
            }
        }

        _maxY = y+1;
    }

    private Vector<Cell> _theMap;
    private int _maxX;
    private int _maxY;
    private Coordinate _entrance;
    private Vector<Character> _keys;
    private Vector<Character> _doors;
    private boolean _debug;
}