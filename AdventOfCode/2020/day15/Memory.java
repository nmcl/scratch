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
        _theMemory = new HashMap<Long, Number>();
        _lastNumberSpoken = -1;
        _turn = 0;
    }

    public long getLastNumber ()
    {
        return _lastNumber;
    }

    public Long speakNumber (Long numb)
    {
        _lastNumberSpoken = numb;
        _turn++l

        Number count = _theMemory.get(numb);

        if (count == null)
            count = new Number();
        else
            count++;

        _theMemory.put(numb, count);

        return count;
    }

    public Long numberPresent (Long numb)
    {
        return -1;
    }

    private HashMap<Long, Number> _theMemory;
    private Long _lastNumberSpoken;
    private Long _turn;
}