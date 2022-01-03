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
    }

    public void addNumber (long numb)
    {

    }

    public long numberPresent (long numb)
    {
        return -1;
    }

    private HashMap<Long, Long> _theMemory;
}