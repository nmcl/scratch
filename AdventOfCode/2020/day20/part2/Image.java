// assemble the entire image from the Tiles

public class Image
{
    public Image(int x, int y)
    {
        _theWorld = new Tile[x][y];

        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < y; j++)
                _theWorld[i][j] = null;
        }
    }

    public void placeTile (Tile theTile, int x, int y)
    {
        if (_theWorld[x][y] != null)
            System.out.println("Warning: overwiting tile < "+x+", "+y+" >");

        _theWorld[x][y] = theTile;
    }

    public Tile getTile (int x, int y)
    {
        return _theWorld[x][y];
    }

    private Tile[][] _theWorld;
    private boolean _debug;
}