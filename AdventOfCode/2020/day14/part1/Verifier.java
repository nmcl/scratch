import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";

    public static final long EXAMPLE_ADDRESS_1 = 7;
    public static final long EXAMPLE_VALUE_1 = 101;
    public static final long EXAMPLE_ADDRESS_2 = 8;
    public static final long EXAMPLE_VALUE_2 = 64;
    public static final long EXAMPLE_TOTAL = 165;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<Command> cmds = Util.loadCommands(EXAMPLE_DATA, _debug);
        Memory mem = new Memory();

        for (int i = 0; i < cmds.size(); i++)
        {
            if (_debug)
                System.out.println("Loaded:\n"+cmds.elementAt(i));

            cmds.elementAt(i).execute(mem);
        }

        if (mem.getValue(EXAMPLE_ADDRESS_1) == EXAMPLE_VALUE_1)
        {
            if (mem.getValue(EXAMPLE_ADDRESS_2) == EXAMPLE_VALUE_2)
            {
                if (mem.getValue(EXAMPLE_ADDRESS_1) + mem.getValue(EXAMPLE_ADDRESS_2) == EXAMPLE_TOTAL)
                    return true;
                else
                    System.out.println("Incorrect total: "+(mem.getValue(EXAMPLE_ADDRESS_1) + mem.getValue(EXAMPLE_ADDRESS_2)));
            }
            else
                System.out.println("Wrong value at memory address "+EXAMPLE_ADDRESS_2+": "+EXAMPLE_VALUE_2);
        }
        else
            System.out.println("Wrong value at memory address "+EXAMPLE_ADDRESS_1+": "+EXAMPLE_VALUE_1);

        return false;
    }

    private boolean _debug;
}