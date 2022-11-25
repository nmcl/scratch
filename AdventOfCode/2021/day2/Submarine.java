import java.util.*;

public class Submarine
{
    public Submarine (boolean debug)
    {
        _position = new ThreeDPoint(0, 0, 0);
        _debug = debug;
    }

    public void move (String dataFile)
    {
        Vector<Command> cmds = Util.loadData(dataFile, _debug);
    }

    private ThreeDPoint _position;
    private boolean _debug;
}