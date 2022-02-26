public class Tile
{
    public Tile (long number, String[] data)
    {
        _id = number;
        _data = data;
    }

    public final long getID ()
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

    private long _id;
    private String[] _data;
}