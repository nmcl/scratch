public class CircularLinkedList
{
    public CircularLinkedList ()
    {
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

    private Node _head = null;
    private Node _tail = null;
}