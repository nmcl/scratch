import java.util.*;

/**
 * A map of the tunnel system. Assume the data
 * has already had the single entrance converted
 * to 4 in this part. If not we'll need to do that
 * convrsion on the fly (TO DO).
 * 
 * Split into 4 Maps and work each robot until it
 * has to wait for another, then swap between them?
 */

public class Map
{
    public Map (Vector<String> input, boolean debug)
    {
        _theMap = new Vector<Cell>();
        _entrances = new Vector<Coordinate>();
        _locationsOfKeys = new Vector<Coordinate>();
        _keys = new Vector<Character>();
        _doors = new Vector<Character>();
        _debug = debug;

        createMap(input);

        if (_entrances.size() == 1)
            transformEntrance();
    }

    public Vector<Coordinate> getEntrances ()
    {
        return _entrances;
    }

    public Vector<Coordinate> getKeyLocations ()
    {
        return _locationsOfKeys;
    }

    /**
     * Return the content of the Cell represented by the Coordinate.
     */

    public char getContent (Coordinate coord)
    {
        int index = _theMap.indexOf(new Cell(coord));
        Cell theCell = _theMap.get(index);

        return theCell.getContents(); // assume no error
    }

    /*
     * Is this Coordinate in range and can be moved into?
     */

    public boolean validPosition (Coordinate coord)
    {
        if ((coord.getX() >= 0) && (coord.getY() >= 0))
        {
            if ((coord.getX() < _maxX) && (coord.getY() < _maxY))
            {
                int index = _theMap.indexOf(new Cell(coord));

                if (index != -1) {
                    Cell theCell = _theMap.get(index);

                    if (theCell != null)
                        return !theCell.isWall();
                }
            }
        }

        return false;
    }

    public int numberOfKeys ()
    {
        return _keys.size();
    }

    public int numberOfDoors ()
    {
        return _doors.size();
    }

    @Override
    public String toString ()
    {
        Enumeration<Cell> iter = _theMap.elements();
        int index = 1;
        String str = "Map <" + _maxX + ", " + _maxY + ">:\n\n";

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
                Cell theCell = new Cell(new Coordinate(x, y), line.charAt(x));

                if (theCell.isEntrance())
                    _entrances.add(theCell.position());
                else
                {
                    if (theCell.isKey())
                    {
                        _locationsOfKeys.add(theCell.position());
                        _keys.add(theCell.getContents());
                    }
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

        _maxY = y + 1;
    }

    /*
     * Transform the single entrace ...
     * 
     * ...
     * .@.
     * ...
     * 
     * to ...
     * 
     * @#@
     * ###
     * @#@
     */

    private void transformEntrance ()
    {
        Cell[] theCells = new Cell[9];
        int index = 0;
        Coordinate theEntrance = _entrances.get(0);  // assume only one!

        System.out.println("Entrance "+theEntrance);

        for (int x = -1; x < 2; x++)
        {
            for (int y = -1; y < 2; y++)
            {
                Cell tempCell = new Cell(new Coordinate(theEntrance.getX() + x, theEntrance.getY() + y));
                int contains = _theMap.indexOf(tempCell);

                if (contains != -1)
                {
                    theCells[index] = _theMap.get(contains);
                    theCells[index].setContents(CellId.STONE_WALL);
                }
                else
                    theCells[index] = null;

                index++;
            }
        }

        _entrances = new Vector<Coordinate>();

        if (theCells[0] != null)
        {
            theCells[0].setContents(CellId.ENTRANCE);
            _entrances.add(theCells[0].position());
        }

        if (theCells[2] != null)
        {
            theCells[2].setContents(CellId.ENTRANCE);
            _entrances.add(theCells[2].position());
        }

        if (theCells[6] != null)
        {
            theCells[6].setContents(CellId.ENTRANCE);
            _entrances.add(theCells[6].position());
        }

        if (theCells[8] != null)
        {
            theCells[8].setContents(CellId.ENTRANCE);
            _entrances.add(theCells[8].position());
        }
    }

    private Vector<Cell> _theMap;
    private int _maxX;
    private int _maxY;
    private Vector<Coordinate> _entrances;
    private Vector<Coordinate> _locationsOfKeys;
    private Vector<Character> _keys;
    private Vector<Character> _doors;
    private boolean _debug;
}