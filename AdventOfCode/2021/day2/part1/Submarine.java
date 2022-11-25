import java.util.*;

public class Submarine
{
    public Submarine (boolean debug)
    {
        _position = new ThreeDPoint(0, 0, 0);
        _debug = debug;
    }

    public ThreeDPoint move (String dataFile)
    {
        Vector<Command> cmds = Util.loadData(dataFile, _debug);

        for (int i = 0; i < cmds.size(); i++)
        {
            Command theCommand = cmds.elementAt(i);

            switch (theCommand.cmd())
            {
                case Command.FORWARD:
                {
                    _position = new ThreeDPoint(_position.getX() + theCommand.amount(), _position.getY(), _position.getZ());
                }
                break;
                case Command.DOWN:
                {
                    _position = new ThreeDPoint(_position.getX(), _position.getY(), _position.getZ() + theCommand.amount());
                }
                break;
                case Command.UP:
                {
                    _position = new ThreeDPoint(_position.getX(), _position.getY(), _position.getZ() - theCommand.amount());
                }
                break;
                default:
                {
                    System.out.println("Unknown command: "+theCommand.cmd());
                }
            }
        }

        return _position;
    }

    private ThreeDPoint _position;
    private boolean _debug;
}