import java.util.*;

public class Submarine
{
    public Submarine (boolean debug)
    {
        _position = new Course(0, 0, 0);
        _debug = debug;
    }

    public Course move (String dataFile)
    {
        Vector<Command> cmds = Util.loadData(dataFile, _debug);

        for (int i = 0; i < cmds.size(); i++)
        {
            Command theCommand = cmds.elementAt(i);

            if (_debug)
                System.out.println("Submarine command: "+theCommand);

            switch (theCommand.cmd())
            {
                case Command.FORWARD:
                {
                    _position = new Course(_position.getPosition() + theCommand.amount(), _position.getAim(), _position.getDepth() * theCommand.amount());
                }
                break;
                case Command.DOWN:
                {
                    _position = new Course(_position.getPosition(), _position.getAim() + theCommand.amount(), _position.getDepth());
                }
                break;
                case Command.UP:
                {
                    _position = new Course(_position.getPosition(), _position.getAim() - theCommand.amount(), _position.getDepth());
                }
                break;
                default:
                {
                    System.out.println("Unknown command: "+theCommand.cmd());
                }
            }

            if (_debug)
                System.out.println("Submarine course: "+_position);
        }

        return _position;
    }

    private Course _position;
    private boolean _debug;
}