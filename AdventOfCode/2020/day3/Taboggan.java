public class Taboggan
{
    public static final int MAP_COPIES = 100;  // hack

    public Taboggan (Map original, boolean debug)
    {
        _maps = new Map[MAP_COPIES];
        _maps[0] = original;
        
        _mapIndex = 0;
        _debug = debug;
    }

    private Map[] _maps;
    private int _mapIndex;
    private boolean _debug;
}