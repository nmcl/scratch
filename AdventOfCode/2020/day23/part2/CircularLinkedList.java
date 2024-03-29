import java.util.*;

public class CircularLinkedList
{
    public CircularLinkedList ()
    {
        _entries = new HashMap<Integer, Node>();
        _head = _tail = _current = null;
    }

    public final HashMap<Integer, Node> getEntries ()
    {
        return _entries;
    }
    
    public final Set<Integer> asList ()
    {
        return _entries.keySet();
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

        removed.add(cup.getValue());

        _entries.remove(cup.getValue());

        for (int i = 0; i < 2; i++)
        {
            entry = entry.getNext();

            removed.add(entry.getValue());

            _entries.remove(entry.getValue());
        }

        if (entry == _tail)
        {
            _tail = position;
            _tail.setNext(_head);
        } 
        else
        {
            if (entry == _head)
            {
                _head = _head.getNext();
                _tail = position;
                _tail.setNext(_head);
            }
            else
            {
                if (cup == _tail)
                {
                    _head = _head.getNext().getNext();
                    _tail = position;
                    _tail.setNext(_head);
                }
                else
                {
                    if (cup == _head)
                    {
                        _head = _head.getNext().getNext().getNext();
                        _tail.setNext(_head);
                    } 
                    else
                        position.setNext(entry.getNext());
                }
            }
        }

        return removed;
    }

    @Override
    public String toString ()
    {
        Iterator<Node> iter = _entries.values().iterator();
        String str = "";

        while (iter.hasNext())
            str += iter.next()+", ";

        return str;
    }

    private HashMap<Integer,Node> _entries;
    private Node _head;
    private Node _tail;
    private Node _current;
}