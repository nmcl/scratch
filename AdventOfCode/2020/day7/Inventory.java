import java.util.*;

public class Inventory
{
    public Inventory (boolean debug)
    {
        _bags = new Vector<Bag>();
        _debug = debug;
    }

    public final void add (Bag b)
    {
        _bags.add(b);
    }

    @Override
    public String toString ()
    {
        String str = "";
        Enumeration<Bag> iter = _bags.elements();

        while (iter.hasMoreElements())
        {
            Bag b = iter.nextElement();

            str += b.printRule()+"\n";
        }

        return str;
    }

    private Vector<Bag> _bags;
    private boolean _debug;
}