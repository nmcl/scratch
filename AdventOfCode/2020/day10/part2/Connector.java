import java.util.*;

public class Connector
{
    public static final int SEAT_JOLTAGE = 0;
    public static final int JOLTAGE_RANGE = 3;

    public Connector (boolean debug)
    {
        _differenceOne = null;
        _differenceTwo = null;
        _differenceThree = null;
        _debug = debug;
    }

    public Vector<JoltageAdapter> getDifferenceOne ()
    {
        return _differenceOne;
    }

    public Vector<JoltageAdapter> getDifferenceTwo ()
    {
        return _differenceTwo;
    }

    public Vector<JoltageAdapter> getDifferenceThree ()
    {
        return _differenceThree;
    }

    public long combinations (Vector<JoltageAdapter> adapters)
    {
        HashMap<Integer, Long> count = new HashMap<Integer, Long>();
        int index = 0;

        return check(adapters, count, index);
    }

    public Vector<JoltageAdapter> connect (Vector<JoltageAdapter> adapters)
    {
        Vector<JoltageAdapter> toReturn = new Vector<JoltageAdapter>();
        int joltage = 0;

        _differenceOne = new Vector<JoltageAdapter>();
        _differenceTwo = new Vector<JoltageAdapter>();
        _differenceThree = new Vector<JoltageAdapter>();

        while (adapters.size() > 0)
        {
            boolean found = false;
            JoltageAdapter adapter = null;

            if (_debug)
                System.out.println("Seaching for adapter joltage: "+joltage);
            
            for (int i = 0; (i < adapters.size()) && !found; i++)
            {
                int current = adapters.elementAt(i).outputJoltage();

                if (_debug)
                    System.out.println("Checking adapter: "+current);

                if (current == joltage)
                {
                    adapter = adapters.elementAt(i);
                    found = true;
                }

                if ((current > joltage) && (current <= joltage + JOLTAGE_RANGE))
                {
                    if (_debug)
                        System.out.println("Adapter "+current+" within range. Have "+adapter);

                    if (adapter == null)
                        adapter = adapters.elementAt(i);
                    else
                    {
                        if (current <= adapter.outputJoltage())
                            adapter = adapters.elementAt(i);
                    }

                    if (_debug)
                        System.out.println("Adapter: "+adapter);
                }
            }

            if (_debug)
                System.out.println("Difference "+(adapter.outputJoltage() - joltage));

            switch (adapter.outputJoltage() - joltage)
            {
                case 1:
                {
                    _differenceOne.add(adapter);
                }
                break;
                case 2:
                {
                    _differenceTwo.add(adapter);
                }
                break;
                case 3:
                {
                    _differenceThree.add(adapter);
                }
                break;
                default:
                {
                    // dp nothing
                }
                break;
            }

            joltage = adapter.outputJoltage();

            toReturn.add(adapter);
            adapters.remove(adapter);
        }

        if (_debug)
        {
            System.out.println("Difference One: "+_differenceOne.size());
            System.out.println("Difference Two: "+_differenceTwo.size());
            System.out.println("Difference Three: "+_differenceThree.size());
        }

        return toReturn;
    }

    private long check (Vector<JoltageAdapter> adapters, HashMap<Integer, Long> count, int index)
    {
        if (index == adapters.size() - 1)
            return 1;

        if (count.containsKey(index))
            return count.get(index);

        long value = 0;

        for (int i = index + 1; i < adapters.size(); i++)
        {
            if (adapters.get(i).outputJoltage() - adapters.get(index).outputJoltage() > JOLTAGE_RANGE)
                break;

            value += check(adapters, count, i);
        }

        count.put(index, value);

        return value;
    }

    private Vector<JoltageAdapter> _differenceOne;
    private Vector<JoltageAdapter> _differenceTwo;
    private Vector<JoltageAdapter> _differenceThree;
    private boolean _debug;
}