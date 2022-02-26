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

    @Override
    public String toString ()
    {
        String str = TileData.TILE_ID+_id+":\n";

        for (int i = 0; i < _data.length; i++)
        {
            for (int j = 0; i < _data[0].length; j++)
            {
                str += _data[j][i];
            }

            str += "\n";
        }

        return str;
    }

    private int _id;
    private char[][] _data;
    private boolean _debug;
}