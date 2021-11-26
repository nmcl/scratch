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

    public void move ()
    {
        boolean finished = false;

        while (!finished)
        {
            if (!_maps[_mapIndex].validPosition(_position))
            {
                _mapIndex++;

                _maps[_mapIndex] = _maps[0];

                _position = new Coordinate(_position.getX() + MOVE_X, _position.getY() + MOVE_Y);
            }

            if (!_maps[_mapIndex].finished(_position))
            {

            }
            else
                finished = true;
        }
    }

    private Map[] _maps;
    private int _mapIndex;
    private int _numberOfTrees;
    private Coordinate _position;
    private boolean _debug;
}