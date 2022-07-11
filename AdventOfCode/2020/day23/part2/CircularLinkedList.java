import java.util.*;

public class CircularLinkedList
{
    public CircularLinkedList ()
    {
        _entries = new HashMap<Integer, Node>();
        _head = _tail = _current = null;
    }

    public final Node getCurrent ()
    {
        return _current;
    }

    public final void setCurrent (Node c)
    {
        _current = c;
    }

    public final void add (int value)
    {
        add(new Node(value));
    }

    public final void add (Node n)
    {
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

        _entries.put(n.getValue(), n);
    }

    public void addTo (Node position, CircularLinkedList toAdd)
    {
        if (position == _tail)
        {
            Node theTail = _tail;

            _tail = toAdd._tail;
            _tail.setNext(_head);
            theTail.setNext(toAdd._head);
        }
        else
        {
            Node posNext = position.getNext();

            position.setNext(toAdd._head);
            toAdd._tail.setNext(posNext);
        }

        _entries.putAll(toAdd._entries);
    }

    public final CircularLinkedList removeFrom (Node position)
    {
        CircularLinkedList removed = new CircularLinkedList();
        Node cup = position.getNext();
        Node entry = cup;

        removed.add(cup);
        _entries.remove(cup.getValue());

        for (int i = 0; i < 2; i++)
        {
            entry = entry.getNext();

            removed.add(entry);
            _entries.remove(entry.getValue());
        }

        if ((entry == _tail) || (entry == _head))
        {
            _tail = position;
            _tail.setNext(_head);

            if (entry == _head)
                _head = _head.getNext();
        }
        else
        {
            if ((cup == _tail) || (cup == _head))
            {
                _head = _head.getNext().getNext();

                if (cup == _head)
                {
                    _head = _head.getNext();
                }
                else
                    _tail = position;

                _tail.setNext(_head);
            }
            else
                position.setNext(entry.getNext());
        }

        return removed;
    }

    private HashMap<Integer, Node> _entries;
    private Node _head;
    private Node _tail;
    private Node _current;
}