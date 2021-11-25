public class Taboggan
{
    public static final int MAP_COPIES = 100;  // hack

    public static final int MOVE_X = 3;
    public static final int MOVE_Y = 1;

    public Taboggan (Map original, boolean debug)
    {
        _maps = new Map[MAP_COPIES];
        _maps[0] = original;

        _position = new Coordinate(0, 0);
        _mapIndex = 0;
        _debug = debug;
    }

    public void move ()
    {
        
    }

    private Map[] _maps;
    private int _mapIndex;
    private Coordinate _position;
    private boolean _debug;
}