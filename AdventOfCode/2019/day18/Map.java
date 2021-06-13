import java.util.*;

/**
 * A map of the tunnel system.
 */

public class Map
{
    public Map (Vector<String> input, boolean debug)
    {
        _theMap = new Vector<Cell>();
        _nodeMap = null;
        _keys = new Vector<Character>();
        _doors = new Vector<Character>();
        _debug = debug;

        createMap(input);
    }

    public Coordinate getEntrance ()
    {
        return _entrance;
    }

    public Node getStartingNode ()
    {
        if (_nodeMap != null)
            return _nodeMap[_entrance.getX()][_entrance.getY()];

        return createGraph();
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

     /*
      * From the map create a graph.
      *
      * Note, at the moment we don't use the graph!
      */

     private Node createGraph ()  // temporary public for testing!
     {
         Enumeration<Cell> iter = _theMap.elements();
         int x = 0;
         int y = 0;
 
         _nodeMap = new Node[_maxX][_maxY];
 
         while (iter.hasMoreElements())
         {
             _nodeMap[x][y] = new Node(iter.nextElement());
 
             x++;
 
             if (x == _maxX)
             {
                 x = 0;
                 y++;
             }
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
                     left = _nodeMap[i-1][j];
                 
                 if (i +1 < _maxX)
                     right = _nodeMap[i +1][j];
 
                 if (j -1 >= 0)
                     up = _nodeMap[i][j -1];
 
                 if (j +1 < _maxY)
                     down = _nodeMap[i][j +1];
 
                 _nodeMap[i][j].setLinks(up, down, left, right);
             }
         }
 
         if (_debug)
         {
             for (x = 0; x < _maxX; x++)
             {
                 for (y = 0; y < _maxY; y++)
                 {
                     System.out.println("\n"+_nodeMap[x][y]);
                 }
             }
         }
 
         return _nodeMap[_entrance.getX()][_entrance.getY()];
     }

    private Vector<Cell> _theMap;
    private Node[][] _nodeMap;
    private int _maxX;
    private int _maxY;
    private Coordinate _entrance;
    private Vector<Character> _keys;
    private Vector<Character> _doors;
    private boolean _debug;
}