import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";

    public static final long EXAMPLE_ADDRESS_1 = 7;
    public static final long EXAMPLE_VALUE_1 = 0;
    public static final long EXAMPLE_ADDRESS_2 = 8;
    public static final long EXAMPLE_VALUE_2 = 64;

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
            System.out.println("Loaded:\n"+cmds.elementAt(i));

            cmds.elementAt(i).execute(mem);
        }

        if (mem.getValue(EXAMPLE_ADDRESS_1) == EXAMPLE_VALUE_1)
        {
            if (mem.getValue(EXAMPLE_ADDRESS_2) == EXAMPLE_VALUE_2)
                return true;
            else
                System.out.println("Wrong value at memory address "+EXAMPLE_ADDRESS_2+": "+EXAMPLE_VALUE_2);
        }
        else
            System.out.println("Wrong value at memory address "+EXAMPLE_ADDRESS_1+": "+EXAMPLE_VALUE_1);

        return false;
    }

    private boolean _debug;
}