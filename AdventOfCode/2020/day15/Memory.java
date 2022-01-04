import java.util.*;

/*
 * They begin by taking turns reading from a list of starting numbers (your puzzle input).
 * Then, each turn consists of considering the most recently spoken number:
 * 
 * If that was the first time the number has been spoken, the current player says 0.
 * Otherwise, the number had been spoken before; the current player announces how many turns
 * apart the number is from when it was previously spoken.
 */

public class Memory
{
    public Memory ()
    {
        _theMemory = new HashMap<Long, Long>();
        _lastNumberSpoken = -1;
    }

    public long getLastNumber ()
    {
        return _lastNumber;
    }

    public void speakNumber (long numb)
    {
        _lastNumberSpoken = numb;

        Long count = _theMemory.get(numb);

        if (count == null)
            count = 0;
        else
            count++;

        _theMemory.put(numb, count);
    }

    public long numberPresent (long numb)
    {
        return -1;
    }

    private HashMap<Long, Long> _theMemory;
    private long _lastNumberSpoken;
}