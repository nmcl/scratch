import java.util.*;

public class Inventory
{
    public Inventory (boolean debug)
    {
        _bags = new Vector<Bag>();
        _debug = debug;
    }

    public final void insert (Bag b)
    {
        _bags.add(b);
    }

    public final int totalBagCount (Bag theBag)
    {
        Vector<Bag> checked = new Vector<Bag>();
        int index = _bags.indexOf(theBag);
        Bag actualBag = _bags.elementAt(index);
        int count = actualBag.totalContainedBags();
        Vector<Bag> containedBags = actualBag.containedBags();

        if (containedBags.size() > 0)
        {
            Enumeration<Bag> iter = containedBags.elements();

            while (iter.hasMoreElements())
            {
                Bag b = iter.nextElement();

                count += totalBagCount(b) * b.quantity();
            }
        }

        return count;
    }

    public final int bagCount (Bag theBag)
    {
        Vector<Bag> checked = new Vector<Bag>();

        return checkBags(theBag, checked);
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

            //str += "    Total contained bags in "+b+": "+b.totalContainedBags()+"\n";
        }

        return str;
    }

    private final int checkBags (Bag theBag, Vector<Bag> checked)
    {
        int count = 0;
        Enumeration<Bag> iter = _bags.elements();
        Vector<Bag> indirect = new Vector<Bag>();

        while (iter.hasMoreElements())
        {
            Bag b = iter.nextElement();

            if (b.contains(theBag))
            {
                if (_debug)
                    System.out.println("A "+b+" bag contains a "+theBag+" bag.");

                if (!checked.contains(b))
                {
                    indirect.add(b);

                    checked.add(b);

                    count++;
                }
            }
        }

        if (indirect.size() > 0)
        {
            iter = indirect.elements();

            while (iter.hasMoreElements())
            {
                Bag b = iter.nextElement();

                count += checkBags(b, checked);
            }
        }

        return count;
    }

    private Vector<Bag> _bags;
    private boolean _debug;
}