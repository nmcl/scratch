public class Tile
{
    public Tile (int number, char[][] data, boolean debug)
    {
        _id = number;
        _data = date;
        _debug = debug;
    }

    public final int getID ()
    {
        return _id;
    }

    private int _id;
    private char[][] _data;
    private boolean _debug;
}