/*
 * A Node wraps a Cell from the Map and includes
 * links to the neighbouring Nodes.
 */

public class Node
{
    public static final int UP_NODE = 0;
    public static final int DOWN_NODE = 1;
    public static final int LEFT_NODE = 2;
    public static final int RIGHT_NODE = 3;

    public Node (Cell theCell)
    {
        _theCell = theCell;
    }

    public boolean hasBeenVisited ()
    {
        return _visited;
    }

    public void markAsVisited ()
    {
        _visited = true;
    }
    
    public Node getAdjacentNode (int direction)
    {
        Node nextNode = null;

        switch (direction)
        {
            case UP_NODE:
                nextNode = _up;
                break;
            case DOWN_NODE:
                nextNode = _down;
                break;
            case LEFT_NODE:
                nextNode = _left;
                break;
            case RIGHT_NODE:
            default:  // assumption! could give error message
                nextNode = _right;
                break;
        }
        
        return nextNode;
    }

    public void setLinks (Node up, Node down, Node left, Node right)
    {
        _up = up;
        _down = down;
        _left = left;
        _right = right;
    }

    /*
     * Only check space is not a wall (for now).
     */

    public boolean traversable ()
    {
        if (_theCell != null)
        {
            if (!_theCell.isWall())
                return true;
        }

        return false;
    }

    public Cell getCell ()
    {
        return _theCell;
    }

    @Override
    public String toString ()
    {
        String str = "Node: "+_theCell+"\nUp link: ";

        if (_up == null)
            str += "empty";
        else
            str += _up.getCell().position();

        str += "\nDown link: ";

        if (_down == null)
            str += "empty";
        else
            str += _down.getCell().position();

        str += "\nLeft link: ";

        if (_left == null)
            str += "empty";
        else
            str += _left.getCell().position();

        str += "\nRight link: ";

        if (_right == null)
            str += "empty";
        else
            str += _right.getCell().position();

        return str;
    }

    @Override
    public int hashCode ()
    {
        return _theCell.hashCode();
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;
        
        if (getClass() == obj.getClass())
        {
            Node temp = (Node) obj;

            return temp._theCell.equals(_theCell);
        }

        return false;
    }

    private Cell _theCell = null;
    private Node _up = null;
    private Node _down = null;
    private Node _left = null;
    private Node _right = null;
    private boolean _visited = false;
}