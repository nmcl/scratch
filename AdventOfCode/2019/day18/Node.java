public class Node
{
    public Node (String value, int steps)
    {
        _data = value;
        _steps = steps;
    }

    private String _data;
    private int _steps;
    private Node _left;
    private Node _right;
}