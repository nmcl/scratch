import java.util.*;

public class CircularLinkedList
{
    public CircularLinkedList ()
    {
        _entries = new HashMap<Integer, Node>();
        _head = _tail = _current = null;
    }

    public void add (int value)
    {
        Node n = new Node(value);
        
        if (_head == null)
            _head = n;
        else
            _tail.setNext(n);

        _tail = n;
        _tail.setNext(_head);
    }

    private HashMap<Integer, Node> _entries;
    private Node _head;
    private Node _tail;
    private Node _current;
}