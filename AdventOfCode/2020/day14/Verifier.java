import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<Command> cmds = Util.loadData(EXAMPLE_DATA, _debug);

        for (int i = 0; i < cmds.size(); i++)
        {
            System.out.println("Loaded "+cmds.elementAt(i));
        }

        return false;
    }

    private boolean _debug;
}