import java.util.*;

/**
 * A map of the tunnel system.
 */

public class Map
{
    public Map (Vector<String> input, boolean debug)
    {
        _theMap = new Vector<Cell>();
        _debug = debug;

        createMap(input);
    }

    public Coordinate getEntrance ()
    {
        Enumeration<Cell> iter = _theMap.elements();

        while (iter.hasMoreElements())
        {
            Cell theCell = iter.nextElement();

            if (theCell.isEntrance())
                return theCell.position();
        }

        return null;  // oops!!
    }

    @Override
    public String toString ()
    {
        Enumeration<Cell> iter = _theMap.elements();
        int index = 1;
        String str = "Map <"+(_maxX+1)+", "+(_maxY+1)+">:\n\n";

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

    private void createMap (Vector<String> input)
    {
        Enumeration<String> iter = input.elements();
        int y = -1;

        _maxX = input.get(0).length(); // all lines are the same length

        while (iter.hasMoreElements())
        {
            y++;

            String line = iter.nextElement();

            for (int x = 0; x < _maxX; x++)
            {
                Cell theCell = new Cell(new Coordinate(x, y), line.charAt(x), _debug);

                if (theCell.isEntrance)
                    _entrance = theCell.position();  // should be only one!

                if (_debug)
                    System.out.println(theCell);

                _theMap.add(theCell);
            }
        }

        _maxY = y;
    }

    private void createGraph ()
    {
        Enumeration<Cell> iter = _theMap.elements();
        Vector<Node> allNodes = new Vector<Node>();

        // first pass ...

        while (iter.hasMoreElements())
        {
            Cell c = iter.nextElement();

            allNodes.add(new Node(c));
        }

        /*
         * We now have a list of disconnected Nodes. Let's
         * connect them into a graph.
         */

        Enumeration reti = allNodes.elements();
        
        while (reti.hasMoreElements())
        {
            Node n = reti.nextElement();
            Coordinate coord = n.getCell.position();
            Coordinate up = getUpPosition(coord);
            Coordinate down = getDownPosition(coord);
            Coordinate left = getLeftPosition(coord);
            Coordinate right = getRightPosition(coord);

            // create or find Nodes. Replace dummy Nodes in the list.
        }
    }

    private Coordinate getUpPosition (Coordinate coord)
    {
        Coordinate pos = new Coordinate(coord.getX(), coord.getY() +1);

        if (pos.getY() < _maxY)
            return pos;
        else
            return null;
    }

    private Coordinate getDownPosition (Coordinate coord)
    {
        Coordinate pos = new Coordinate(coord.getX(), coord.getY() -1);

        if (pos.getX() >= 0)
            return pos;
        else
            return null;
    }

    private Coordinate getLeftPosition (Coordinate coord)
    {
        Coordinate pos = new Coordinate(coord.getX() -1, coord.getY());

        if (pos.getX() >= 0)
            return pos;
        else
            return null;
    }

    private Coordinate getRightPosition (Coordinate coord)
    {
        Coordinate pos = new Coordinate(coord.getX() +1, coord.getY());

        if (pos.getY() < _maxX)
            return pos;
        else
            return null;
    }

    private Vector<Cell> _theMap;
    private int _maxX;
    private int _maxY;
    private Coordinate _entrance;
    private boolean _debug;
}