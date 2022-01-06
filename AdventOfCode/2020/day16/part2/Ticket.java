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

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;
        
        if (getClass() == obj.getClass())
        {
            Ticket temp = (Ticket) obj;

            if (temp._values.length == _values.length)
            {
                for (int i = 0; i < _values.length; i++)
                {
                    if (temp._values[i] != _values[i])
                        return false;
                }

                return true;
            }
        }

        return false;
    }

    @Override
    public String toString ()
    {
        String str = "Ticket: ";

        for (int i = 0; i < _values.length; i++)
            str += _values[i]+" ";

        return str;
    }

    private int[] _values;
}