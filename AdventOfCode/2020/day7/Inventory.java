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

    private Vector<Bag> _bags;
    private boolean _debug;
}