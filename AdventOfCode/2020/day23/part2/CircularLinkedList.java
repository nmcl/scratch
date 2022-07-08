public class CircularLinkedList
{
    public CircularLinkedList ()
    {
        _size = 0;
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

    private int _size;
    private Node _head;
    private Node _tail;
    private Node _current;
}