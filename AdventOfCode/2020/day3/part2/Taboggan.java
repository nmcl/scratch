public class Taboggan
{
    public static final int MAP_COPIES = 100;  // hack

    public Taboggan (Map original, boolean debug)
    {
        _maps = new Map[MAP_COPIES];
        _maps[0] = original;

        _mapIndex = 0;
        _numberOfTrees = 0;
        _position = new Coordinate(0, 0);
        _debug = debug;
    }

    public final void reset ()
    {
        _maps = new Map[MAP_COPIES];
        _maps[0] = original;

        _mapIndex = 0;
        _numberOfTrees = 0;
        _position = new Coordinate(0, 0);
    }

    public int move (Coordinate jump)
    {
        boolean finished = false;

        while (!finished)
        {
            if (_debug)
                System.out.println("Taboggan on map "+_mapIndex+" and at "+_position);

            if (!_maps[_mapIndex].validPosition(_position))
            {
                if (_debug)
                    System.out.println("Invalid position so moving to next map.");

                _mapIndex++;

                _maps[_mapIndex] = _maps[0];

                _position = new Coordinate(_position.getX() - _maps[0].width(), _position.getY());

                if (_debug)
                    System.out.println("New position "+_position);
            }
            else
            {
                if (_debug)
                    System.out.println("Valid position on this map.");
            }

            if (!_maps[_mapIndex].finished(_position))
            {
                if (_debug)
                    System.out.println("Not finished this map.");

                if (_maps[_mapIndex].elementType(_position) == MapElement.TREE)
                    _numberOfTrees++;

                _position = new Coordinate(_position.getX() + jump.getX(), _position.getY() + jump.getY());
            }
            else
                finished = true;
        }

        return _numberOfTrees;
    }

    private Map[] _maps;
    private int _mapIndex;
    private int _numberOfTrees;
    private Coordinate _position;
    private boolean _debug;
}