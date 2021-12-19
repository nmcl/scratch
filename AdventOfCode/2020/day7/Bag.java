import java.util.*;

public class Bag
{
    public Bag (String type)
    {
        this(type, 0);
    }

    public Bag (String type, int quantity)
    {
        _type = type.trim();
        _quantity = quantity;
        _contains = new Vector<Bag>();
    }
    
    public void add (Bag b)
    {
        System.out.println("**NOW "+b+" and "+b.quantity());

        _contains.add(b);
    }

    public final int quantity ()
    {
        return _quantity;
    }

    public String printRule ()
    {
        String str = this+" bags "+Rules.CONTAINS;

        if (_contains.size() > 0)
        {
            Enumeration<Bag> iter = _contains.elements();

            while (iter.hasMoreElements())
            {
                Bag b = iter.nextElement();

                if (b._quantity > 0)
                {
                    str += " "+_quantity;
                }

                str += " "+b;

                if (iter.hasMoreElements())
                    str += Rules.SEPARATOR;
            }
        }
        else
            str += Rules.NO_BAGS;

        str += Rules.TERMINATOR;

        return str;
    }

    @Override
    public String toString ()
    {
        return _type;
    }

    @Override
    public int hashCode ()
    {
        return Objects.hash(_type);
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
            Bag temp = (Bag) obj;

            return temp._type.equals(_type);
        }

        return false;
    }

    private String _type;
    private int _quantity;
    private Vector<Bag>_contains;
}