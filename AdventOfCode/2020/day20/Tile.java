public class Tile
{
    public Tile (int number, String[] data, boolean debug)
    {
        _id = number;
        _data = date;
        _debug = debug;
    }

    public final int getID ()
    {
        return _id;
    }

    @Override
    public String toString ()
    {
        String str = TileData.TILE_ID+_id+":\n";

        for (int i = 0; i < _data.length; i++)
            str += _data[i]+"\n";

        return str;
    }

    private int _id;
    private String[] _data;
    private boolean _debug;
}