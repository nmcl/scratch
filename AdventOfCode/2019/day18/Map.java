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



        
        Vector<Node> graph = new Vector<Node>();
        
        while (iter.hasMoreElements())
        {
            Cell c = iter.nextElement();

            Coordinate coord = c.position();
            Coordinate up = getUpPosition(coord);
            Coordinate down = getDownPosition(coord);
            Coordinate left = getLeftPosition(coord);
            Coordinate right = getRightPosition(coord);

            /*
             * Check to see if the Node is already part.
             */

            int upIndex = graph.indexOf(new Node(up));
            Node upNode = ((upIndex == -1) ? null : allNodes.elementAt(upIndex));

            int downIndex = allNodes.indexOf(new Node(down));
            Node downNode = ((downIndex == -1) ? null : allNodes.elementAt(downIndex));

            int leftIndex = allNodes.indexOf(new Node(left));
            Node leftNode = ((leftIndex == -1) ? null : allNodes.elementAt(leftIndex));

            int rightIndex = allNodes.indexOf(new Node(right));
            Node rightNode = ((rightIndex == -1) ? null : allNodes.elementAt(rightIndex));

            Node theNode = new Node(n.getCell(), upNode, downNode, leftNode, rightNode);

            graph.add(theNode);
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