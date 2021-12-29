import java.util.*;

public class Verifier
{
    public static final String EXAMPLE = "example.txt";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<Command> commands = Util.loadCommands(EXAMPLE, _debug);
        Ship theShip = new Ship(_debug);

        theShip.move(commands);

        System.out.println(theShip);

        return false;
    }

    private boolean _debug;
}