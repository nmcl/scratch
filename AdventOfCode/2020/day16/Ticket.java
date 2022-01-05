public class Ticket
{
    public Ticket (String[] range)
    {
        _values = new int[range.length];

        for (int i = 0; i < range.length; i++)
        {
            _values[i] = Integer.parseInt(range[i]);
        }
    }

    public int[] values ()
    {
        return _values;
    }

    private int[] _values;
}