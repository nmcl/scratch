import java.util.*;

public class Memory
{
    public static final long UNASSIGNED_MEMORY = 0;

    public Memory ()
    {
        _memory = new HashMap<Long, Long>();
    }

    public long getValue (long address)
    {
        Long v = _memory.get(address);

        return ((v == null) ? UNASSIGNED_MEMORY : v);
    }

    public void setValue (long address, long value)
    {
        _memory.put(address, value);
    }

    public long total ()
    {
        Collection<Long> values = _memory.values();
        Iterator<Long> iter = values.iterator();
        long total = 0;

        while (iter.hasNext())
        {
            total += iter.next();
        }

        return total;
    }

    private HashMap<Long, Long> _memory;
}