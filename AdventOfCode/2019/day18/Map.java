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

        return null;  // oops!! shouldn't happen.
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

    // create the map from input
    
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

                System.out.println("Created cell "+theCell);

                if (theCell.isEntrance())
                    _entrance = theCell.position();  // should be only one!

                if (_debug)
                    System.out.println(theCell);

                _theMap.add(theCell);
            }
        }

        _maxY = y;
    }

    // from the map create a graph

    public void createGraph ()  // temporary public for testing!
    {
        Node[][] nodeMap = new Node[_maxX][_maxY];
        Enumeration<Cell> iter = _theMap.elements();
        int x = 0;
        int y = 0;

        while (iter.hasMoreElements())
        {
            nodeMap[x][y] = new Node(iter.nextElement());

            System.out.println("Node at "+x+", "+y+" is "+nodeMap[x][y]);
            
            x++;
            y++;

            if (x == _maxX)
                x = 0;

            if (y == _maxY)
                y = 0;
        }

        for (int i = 0; i < _maxX; i++)
        {
            for (int j = 0; j < _maxY; j++)
            {
                Node up = null;
                Node down = null;
                Node left = null;
                Node right = null;

                if (i -1 >= 0)
                    left = nodeMap[i-1][j];
                
                if (i +1 < _maxX)
                    right = nodeMap[i +1][j];

                if (j -1 >= 0)
                    up = nodeMap[i][j -1];

                if (j +1 < _maxY)
                    down = nodeMap[i][j +1];

                nodeMap[i][j].setLinks(up, down, left, right);
            }
        }

        for (x = 0; x < _maxX; x++)
        {
            for (y = 0; y < _maxY; y++)
            {
                System.out.println(nodeMap[x][y]);
            }
        }
    }

    private Vector<Cell> _theMap;
    private int _maxX;
    private int _maxY;
    private Coordinate _entrance;
    private boolean _debug;
}