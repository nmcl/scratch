public class Tile
{
    public static final int TOP = 0;
    public static final int LEFT = 1;
    public static final int BOTTOM = 2;
    public static final int RIGHT = 3;

    public static final int EDGES = 4;

    public Tile (long number, String[] data)
    {
        _id = number;
        _data = data;
        _edges = new String[4];

        _originalState = new String[_data.length];

        for (int i = 0; i < _originalState.length; i++)
        {
            _originalState[i] = _data[i];
        }
        
        generateEdges();
    }

    // rotates tile 90 degrees clockwise

    public void rotate ()
    {
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

    /*
     * Need to consider edge flipping too!!
     */

    private void generateEdges ()
    {
        _edges[TOP] = _data[0];
        _edges[LEFT] = "";
        _edges[BOTTOM] = _data[_data.length -1];
        _edges[RIGHT] = "";

        for (int i = 0; i < _data.length; i++)
        {
            _edges[RIGHT] += _data[i].charAt(0);
            _edges[LEFT] += _data[i].charAt(_data[i].length() -1);
        }

        /*
        System.out.println("\nEdges for "+_id);

        for (int j = 0; j < 4; j++)
        {
            System.out.println(_edges[j]);
        }*/
    }

    private long _id;
    private String[] _data;
    private String[] _originalState;
    private String[] _edges;
}