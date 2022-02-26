public class Tile
{
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    public Tile (long number, String[] data)
    {
        _id = number;
        _data = data;

        generateEdges();
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

    private void generateEdges ()
    {

    }

    private long _id;
    private String[] _data;
    private String[] _edges;
}