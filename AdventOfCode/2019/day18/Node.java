/*
 * A Node wraps a Cell from the Map and includes
 * links to the neighbouring Nodes.
 */

public class Node
{
    public Node (Cell theCell, Node up, Node down, Node left, Node right)
    {
        _theCell = theCell;
        _up = up;
        _down = down;
        _left = left;
        _right = right;
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

    private Cell _theCell;
    private Node _up;
    private Node _down;
    private Node _left;
    private Node _right;
}