public class Node
{
    public Node (int value)
    {
        _value = value;
    }

    public void setNext (Node n)
    {
        _next = n;
    }
    
    @Override
    public String toString ()
    {
        return "Node: "+_value;
    }

    private int _value;
    private Node _next;
}