import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";
    public static final int ACCUMULATOR = 8;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<OpCode> instructions = Util.loadData(EXAMPLE_DATA, _debug);
        Computer theComputer = new Computer(_debug);
        int value = theComputer.fixAndExecute(instructions);

        if (value == ACCUMULATOR)
            return true;

        System.out.println("Incorrect accumulator: "+value);
        
        return false;
    }

    private boolean _debug;
}