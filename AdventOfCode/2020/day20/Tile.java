public class Tile
{
    public static final int TOP = 0;
    public static final int LEFT = 1;
    public static final int BOTTOM = 2;
    public static final int RIGHT = 3;

    public Tile (long number, String[] data)
    {
        _id = number;
        _data = new char[data.length][data[0].length()];
        _originalState = new char[data.length][data[0].length()];

        for (int i = 0; i < data.length; i++)
        {
            for (int j = 0; j < data[0].length(); j++)
            {
                _data[i][j] = data[i].charAt(j);
                _originalState[i][j] = data[i].charAt(j);
            }
        }

        _freeze = false;  // should the tile move?

        for (int i = 0; i < 4; i++)
        {
            _connections[i] = false;  // which edges are connected?
        }
    }

    public final long getID ()
    {
        return _id;
    }

    /*
     * rotates tile 90 degrees clockwise. Assume you
     * have to call it multiple times to get through
     * all 360 degrees.
     */

    public void rotate ()
    {
        int x = _data.length;
	    int y = _data[0].length;
	    char[][] temp = new char[x][y];

        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < y; j++)
            {
                temp[j][x - 1 - i] = _data[i][j];
            }
        }

        _data = temp;
    }

    /*
     * Flip top to bottom.
     */

    public void invert ()
    {
        int x = _data.length;
	    int y = _data[0].length;
	    char[][] temp = new char[x][y];

        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < y; j++)
            {
                temp[i][_data[i].length - j - 1] = _data[i][j];
            }
        }

        _data = temp;
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
        {
            for (int j = 0; j < _data[0].length; j++)
            {
                str += _data[i][j];
            }

            str += "\n";
        }

        return str;
    }

    private long _id;
    private char[][] _data;
    private char[][] _originalState;
    private boolean _freeze;
    private boolean[] _connections;
}