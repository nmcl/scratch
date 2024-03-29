import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";
    public static final int ACCUMULATOR = 5;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<OpCode> instructions = Util.loadData(EXAMPLE_DATA, _debug);
        Computer theComputer = new Computer(_debug);
        int acc = theComputer.executeUntilInfiniteLoop(instructions);

        if (acc == ACCUMULATOR)
            return true;

        System.out.println("Wrong accumulator value: "+acc+"\n");
        
        return false;
    }

    private boolean _debug;
}