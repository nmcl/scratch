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
        _edges = new String[4];

        generateEdges();
    }

    public final long getID ()
    {
        return _id;
    }

    public final String[] getEdges ()
    {
        return _edges;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;
        
        if (getClass() == obj.getClass())
        {
            Tile temp = (Tile) obj;

            return (_id == temp._id);
        }

        return false;
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
        _edges[NORTH] = _data[0];
        _edges[EAST] = "";
        _edges[SOUTH] = _data[_data.length -1];
        _edges[WEST] = "";

        for (int i = 0; i < _data.length; i++)
        {
            _edges[WEST] += _data[i].charAt(0);
            _edges[EAST] += _data[i].charAt(_data[i].length() -1);
        }

        System.out.println("\nEdges for "+_id);

        for (int j = 0; j < 4; j++)
        {
            System.out.println(_edges[j]);
        }
    }

    private long _id;
    private String[] _data;
    private String[] _edges;
}