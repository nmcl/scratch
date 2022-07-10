// element of circular linked list

public class Node
{
    public Node (int value)
    {
        _value = value;
    }

    public final int getValue ()
    {
        return _value;
    }

    public final Node getNext ()
    {
        return _next;
    }
    
    public final void setNext (Node n)
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