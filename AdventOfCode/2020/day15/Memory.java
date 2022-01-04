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
        _theMemory = new HashMap<Integer, Number>();
        _turn = 0;
    }

    public Integer speakNumber (Integer numb)
    {
        _turn++;

        Number theNumber = _theMemory.get(numb);

        if (theNumber == null)
            theNumber = new Number(numb, _turn);
        else
            theNumber.spoken(numb);;

        _theMemory.put(numb, theNumber);

        return theNumber.spokenTimes();
    }

    public boolean firstTimeSpoken (Integer number)
    {
        Number numb = _theMemory.get(number);

        if (numb == null)  // not spoken yet!
            return true;
        else
            return (numb.spokenTimes() == 1);
    }

    public Integer getTurnDifference (Integer number)
    {
        Number numb = _theMemory.get(number);

        if (numb == null)
            return 0;
        else
            return numb.turnDifference();
    }

    private HashMap<Integer, Number> _theMemory;
    private Integer _turn;
}