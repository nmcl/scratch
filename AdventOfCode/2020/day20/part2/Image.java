public class Image
{
    public Image(int x, int y)
    {
        _theWorld = new Tile[x][y];
    }

    public void placeTile (Tile theTile, int x, int y)
    {
        _theWorld[x][y] = theTile;
    }

    public Tile getTile (int x, int y)
    {
        return _theWorld[x][y];
    }

    private Tile[][] _theWorld;
}