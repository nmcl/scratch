import java.util.*;

public class Ship
{
    public Ship (boolean debug)
    {
        _position = new Coordinate(0, 0);
        _waypoint = new Coordinate(10, 1);
        _debug = debug;
    }

    public final Coordinate getWaypoint ()
    {
        return _waypoint;
    }

    public final Coordinate getPosition ()
    {
        return _position;
    }

    public final int getManhattanDistance ()
    {
        return Math.abs(_position.getX()) + Math.abs(_position.getY());
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
                System.out.println("Waypoint position: "+_waypoint);
                System.out.println("Executing "+c);
            }

            switch (c.action())
            {
                case Action.FORWARD:
                {
                    int x = _position.getX() + (_waypoint.getX() * c.quantity());
                    int y = _position.getY() + (_waypoint.getY() * c.quantity());

                    _position = new Coordinate(x, y);
                }
                break;
                case Action.EAST:
                {
                    _waypoint = new Coordinate(_waypoint.getX() + c.quantity(), _waypoint.getY());
                }
                break;
                case Action.WEST:
                {
                    _waypoint = new Coordinate(_waypoint.getX() - c.quantity(), _waypoint.getY());
                }
                break;
                case Action.NORTH:
                {
                    _waypoint = new Coordinate(_waypoint.getX(), _waypoint.getY() + c.quantity());
                }
                break;
                case Action.SOUTH:
                {
                    _waypoint = new Coordinate(_waypoint.getX(), _waypoint.getY() - c.quantity());
                }
                break;
                case Action.LEFT:
                {
                    int rotation = c.quantity();

                    while (rotation > 0)
                    {
                        int x = -_waypoint.getY();
                        int y = _waypoint.getX();

                        _waypoint = new Coordinate(x, y);

                        rotation -= 90;
                    }
                }
                break;
                case Action.RIGHT:
                {
                    int rotation = c.quantity();

                    while (rotation > 0)
                    {
                        int x = _waypoint.getY();
                        int y = -_waypoint.getX();

                        _waypoint = new Coordinate(x, y);

                        rotation -= 90;
                    }
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
        return "Ship position "+_position+" and waypoint "+_waypoint;
    }

    private Coordinate _position;
    private Coordinate _waypoint;
    private boolean _debug;
}