import java.util.*;

public class Ship
{
    public Ship (boolean debug)
    {
        _facing = Direction.EAST;
        _position = new Coordinate(0, 0);
        _debug = debug;
    }

    public final char getFacing ()
    {
        return _facing;
    }

    public final Coordinate getPosition ()
    {
        return _position;
    }

    public final void move (Vector<Command> commands)
    {
        Enumeration<Command> iter = commands.elements();

        while (iter.hasMoreElements())
        {
            Command c = iter.nextElement();

            if (_debug)
            {
                System.out.println("Ship position: "+_position);
                System.out.println("Ship executing "+c);
            }

            // if FORWARD then move in the direction defined by our facing

            if (c.action() == Action.FORWARD)
                c = new Command(_facing, c.quantity());

            switch (c.action())
            {
                case Action.EAST:
                {
                    _position = new Coordinate(_position.getX() - c.quantity(), _position.getY());
                }
                break;
                case Action.WEST:
                {
                    _position = new Coordinate(_position.getX() + c.quantity(), _position.getY());
                }
                break;
                case Action.NORTH:
                {
                    _position = new Coordinate(_position.getX(), _position.getY() + c.quantity());
                }
                break;
                case Action.SOUTH:
                {
                    _position = new Coordinate(_position.getX(), _position.getY() - c.quantity());
                }
                break;
                case Action.LEFT:
                case Action.RIGHT:
                {
                    _facing = Util.getNextFacing(_facing, c.action());
                }
                break;
                default:
                {
                    System.out.println("Unexpected command: "+c);
                }
                break;
            }
        }
    }

    @Override
    public String toString ()
    {
        return "Ship facing "+_facing+" and position "+_position;
    }

    private char _facing;
    private Coordinate _position;
    private boolean _debug;
}