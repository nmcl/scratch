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
        _isConnected = new boolean[4];  // edges connected?
        _connections = new long[4];  // if so, which ones?

        for (int i = 0; i < 4; i++)
        {
            _isConnected[i] = false;  // which edges are connected?
            _connections[i] = 0;
        }

        _numberOfConnections = 0;
    }

    public Tile (long number, char[][] data)
    {
        _id = number;
        _data = data;
        _originalState = new char[_data.length][_data[0].length];

        for (int i = 0; i < _data.length; i++)
        {
            for (int j = 0; j < _data[0].length; j++)
            {
                _originalState[i][j] = _data[i][j];
            }
        }

        _freeze = false;  // should the tile move?
        _isConnected = new boolean[4];  // edges connected?
        _connections = new long[4];  // if so, which ones?

        for (int i = 0; i < 4; i++)
        {
            _isConnected[i] = false;  // which edges are connected?
            _connections[i] = 0;
        }

        _numberOfConnections = 0;
    }

    // Take this Tile and return a borderless version.

    public final Tile removeBorders ()
    {
        char[][] borderless = new char[_data.length -2][_data[0].length-2];

        for (int i = 1; i < _data.length -1; i++)
        {
            for (int j = 1; j < _data[0].length -1; j++)
            {
                borderless[i-1][j-1] = _data[i][j];
            }
        }

        return new Tile(getID(), borderless);
    }

    /*
     * Check for this pattern ...
     * 
     *                   # 
     * #    ##    ##    ###
     *  #  #  #  #  #  #   
     */

    public final int numberOfSeaMonsters ()
    {
        int total = 0;

        for (int i = 0; i < _data.length; i++)
        {
            for (int j = 0; j < _data[i].length; j++)
            {
                if ((i < _data.length - SeaMonster.HEIGHT + 1) && (j < _data[i].length - SeaMonster.WIDTH + 1) && (i != 0))
                {
                    if (hasSeaMonster(j, i))
                        total++;
                }
            }
        }

        return total;
    }

    public final int getNumberOfConnections ()
    {
        return _numberOfConnections;
    }

    public final long getID ()
    {
        return _id;
    }

    public final boolean isFrozen ()
    {
        return _freeze;
    }

    public final void freeze ()
    {
        _freeze = true;
    }

    public final boolean[] getConnectionStatuses ()
    {
        return _isConnected;
    }

    public final long[] getConnections ()
    {
        return _connections;
    }

    public String line (int index)
    {
        return new String(_data[index]);
    }

    public int numberOfLines ()
    {
        return _data.length;
    }

    public int numberOfHashes ()
    {
        int count = 0;

        for (int i = 0; i < _data.length; i++)
        {
            for (int j = 0; j < _data[0].length; j++)
            {
                if (_data[i][j] == TileData.HASH)
                    count++;
            }
        }

        return count;
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

    public boolean connectTopToBottom (Tile toCheck)
    {
        // check edges match

        // store the edges separately again?

        for (int i = 0; i < _data.length; i++)
        {
            if (_data[0][i] != toCheck._data[_data.length - 1][i])
                return false;
        }

        connect(toCheck, TOP, BOTTOM);

        return true;
    }

    public boolean connectBottomToTop (Tile toCheck)
    {
        // check edges match

        for (int i = 0; i < _data.length; i++)
        {
            if (_data[_data.length - 1][i] != toCheck._data[0][i])
                return false;
        }

        connect(toCheck, BOTTOM, TOP);

        return true;
    }

    public boolean connectLeftToRight (Tile toCheck)
    {
        // check edges match

        for (int i = 0; i < _data.length; i++)
        {
            if (_data[i][0] != toCheck._data[i][_data.length - 1])
                return false;
        }

        connect(toCheck, LEFT, RIGHT);

        return true;
    }

    public boolean connectRightToLeft (Tile toCheck)
    {
        // check edges match

        for (int i = 0; i < _data.length; i++)
        {
            if (_data[i][_data.length - 1] != toCheck._data[i][0])
                return false;
        }

        connect(toCheck, RIGHT, LEFT);

        return true;
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

            // Tiles are equal if their ids and sizes are the same.

            return ((_id == temp._id) && ((_data.length == temp._data.length) && (_data[0].length == temp._data[0].length)));
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

    private final boolean hasSeaMonster (int x, int y)
    {
        if ((_data[y + 1][x + 1] == TileData.HASH) && (_data[y + 1][x + 4] == TileData.HASH)
            && (_data[y][x + 5] == TileData.HASH) && (_data[y][x + 6] == TileData.HASH)
		    && (_data[y + 1][x + 7] == TileData.HASH) && (_data[y + 1][x + 10] == TileData.HASH)
            && (_data[y][x + 11] == TileData.HASH) && (_data[y][x + 12] == TileData.HASH)
            && (_data[y + 1][x + 13] == TileData.HASH) && (_data[y + 1][x + 16] == TileData.HASH)
		    && (_data[y][x + 17] == TileData.HASH) && (_data[y][x + 18] == TileData.HASH)
            && (_data[y][x + 19] == TileData.HASH) && (_data[y - 1][x + 18] == TileData.HASH))
        {
	        return true;
	    }

	    return false;
    }

    private final void connect (Tile toCheck, int thisEdge, int otherEdge)
    {
        if ((_connections[thisEdge] == 0) && (toCheck._connections[otherEdge] == 0))
        {
            toCheck.freeze();
            freeze();

            _connections[thisEdge] = toCheck.getID();
            _isConnected[thisEdge] = true;
            _numberOfConnections++;
            toCheck._connections[otherEdge] = getID();
            toCheck._isConnected[otherEdge] = true;
            toCheck._numberOfConnections++;
        }
    }

    private long _id;
    private char[][] _data;
    private char[][] _originalState;
    private boolean _freeze;
    private boolean[] _isConnected;
    private long[] _connections;
    private int _numberOfConnections;
}
