import java.util.*;

public class Droid
{
    public Droid (Vector<String> values, boolean debug)
    {
        _theComputer = new Intcode(values, debug);
        _debug = debug;
    }

    /*
     * Move around automatically. TBD.
     */

    public void traverse ()
    {

    }

    public void stepTraverse ()
    {
        boolean finished = false;

        while (!finished)
        {
            _theComputer.executeUntilInput();

            LinkedList<String> outputs = _theComputer.getOutputs();
            Iterator<String> iter = outputs.descendingIterator();

            while (iter.hasNext())
            {
                System.out.println(iter.next());
            }

            finished = true;
        }
    }

    private Intcode _theComputer;
    private boolean _debug;
}