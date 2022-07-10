import java.util.*;

public class CircularLinkedList
{
    public CircularLinkedList ()
    {
        _entries = new HashMap<Integer, Node>();
        _head = _tail = _current = null;
    }

    public final void Node getCurrent ()
    {
        return _current;
    }

    public final void setCurrent (Node c)
    {
        _current = c;
    }

    public final void add (int value)
    {
        Node n = new Node(value);
        
        if (_entries.size() == 0)
        {
            _head = n;
            _tail = _head;
            _current = _head;
            _current.setNext(_head);
        }
        else
        {
            Node last = _tail;

            last.setNext(n);
            n.setNext(_head);
            _tail = n;
        }

        _entries.put(value, n);
    }

    private HashMap<Integer, Node> _entries;
    private Node _head;
    private Node _tail;
    private Node _current;
}