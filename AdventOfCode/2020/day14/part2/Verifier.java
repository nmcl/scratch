import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";
    public static final long EXAMPLE_TOTAL = 208;

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

        if (mem.total() == EXAMPLE_TOTAL)
            return true;
        else
            System.out.println("Wrong total value: "+mem.total());

        return false;
    }

    private boolean _debug;
}