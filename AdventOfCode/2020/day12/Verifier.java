import java.util.*;

public class Verifier
{
    public static final String EXAMPLE = "example.txt";
    public static final Coordinate EXAMPLE_COORDINATE = new Coordinate(17, -8);

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<Command> commands = Util.loadCommands(EXAMPLE, _debug);
        Ship theShip = new Ship(_debug);

        theShip.move(commands);

        if (_debug)
            System.out.println(theShip);

        if (EXAMPLE_COORDINATE.equals(theShip.getPosition()))
            return true;

        System.out.println("Invalid final ship position: "+theShip.getPosition());

        return false;
    }

    private boolean _debug;
}