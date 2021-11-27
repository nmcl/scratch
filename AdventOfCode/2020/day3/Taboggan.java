public class Taboggan
{
    public static final int MAP_COPIES = 100;  // hack

    public static final int MOVE_X = 3;
    public static final int MOVE_Y = 1;

    public Taboggan (Map original, boolean debug)
    {
        _maps = new Map[MAP_COPIES];
        _maps[0] = original;

        _mapIndex = 0;
        _numberOfTrees = 0;
        _position = new Coordinate(0, 0);
        _debug = debug;
    }

    public int move ()
    {
        boolean finished = false;

        while (!finished)
        {
            if (_debug)
                System.out.println("Taboggan on map "+_mapIndex+" and at "+_position);

            if (!_maps[_mapIndex].validPosition(_position))
            {
                if (_debug)
                    System.out.println("Invalid position to moving to next map.");

                _mapIndex++;

                _maps[_mapIndex] = _maps[0];

                _position = new Coordinate(_position.getX() + MOVE_X, _position.getY() + MOVE_Y);
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