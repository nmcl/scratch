import java.util.*;

public class Category
{
    public Category (String type, int r1, int r2, int r3, int r4, boolean debug)
    {
        _name = type;
        _r1 = r1;
        _r2 = r2;
        _r3 = r3;
        _r4 = r4;
        _debug = debug;
    }

    public boolean valid (int i)
    {
        if ((i >= _r1) && (i <= _r2))
            return true;

        if ((i >= _r3) && (i <= _r4))
            return true;

        return false;
    }

    public boolean validTicket (Ticket t)
    {
        int count = 0;
        int[] values = t.values();

        for (int i = 0; i < values.length; i++)
        {
            if (valid(values[i]))
                count++;
        }

        if (count == 3)
            return true;
        else
            return false;
    }

    public boolean validTickets (Vector<Ticket> t)
    {
        boolean[] valid = new boolean[t.size()];

        for (int i = 0; i < t.size(); i++)
        {
            System.out.println(this+" checking validity of "+t.elementAt(i));

            if (!validTicket(t.elementAt(i)))
            {
                System.out.println("invalid");

                valid[i] = false;
            }
            else
            {
                System.out.println("valid");

                if (!valid[i])
                    valid[i] = true;
            }
        }

        for (int j = 0; j < valid.length; j++)
        {
            if (!valid[j])
                return false;
        }

        return true;
    }

    public final String getType ()
    {
        return _name;
    }

    @Override
    public String toString ()
    {
        return "Category "+_name+": "+_r1+"-"+_r2+" or "+_r3+"-"+_r4;
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
            Category temp = (Category) obj;

            return _name.equals(temp._name);
        }

        return false;
    }

    private String _name;
    private int _r1, _r2;
    private int _r3, _r4;
    private boolean _debug;
}