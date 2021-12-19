import java.util.*;

public class Bag
{
    public Bag (String type)
    {
        this(type, 0);
    }

    public Bag (String type, int quantity)
    {
        _type = type;
        _quantity = quantity;
        _contains = new Vector<Bag>();
    }
    
    public void add (Bag b)
    {
        _contains.add(b);
    }

    @Override
    public String toString ()
    {
        String str = "Bag colour: "+_type+" "+Rules.CONTAINS;

        if (_contains.size() > 0)
        {
            Enumeration<Bag> iter = _contains.elements();

            while (iter.hasMoreElements())
            {
                Bag b = iter.nextElement();

                if (b._quantity > 0)
                {
                    
                }
            }
        }
        else
            str += Rules.NO_BAGS;

        return str;
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