/*
 * A Node wraps a Cell from the Map and includes
 * links to the neighbouring Nodes.
 */

public class Node
{
    public Node (Cell theCell)
    {
        _theCell = theCell;
    }

    public void setLinks (Node up, Node down, Node left, Node right)
    {
        _up = up;
        _down = down;
        _left = left;
        _right = right;
    }

    public Cell getCell ()
    {
        return _theCell;
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
}