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

            switch (c.action())
            {
                case Action.EAST:
                {
                    _position = new Coordinate(_position.getX() - c.distance(), _position.getY());
                }
                break;
                case Action.WEST:
                {
                    _position = new Coordinate(_position.getX() + c.distance(), _position.getY());
                }
                break;
                case Action.NORTH:
                {
                    _position = new Coordinate(_position.getX(), _position.getY() + c.distance());
                }
                break;
                case Action.SOUTH:
                {
                    _position = new Coordinate(_position.getX(), _position.getY() - c.distance());
                }
                break;
                case Action.LEFT:
                {

                }
                break;
                case Action.RIGHT:
                {

                }
                break;
                case Action.FORWARD:
                {
                }
                break;
                default:
                {
                    System.out.println("Unknown action: "+action);
                }
                break;
            }
        }
    }

    private char _facing;
    private Coordinate _position;
    private boolean _debug;
}