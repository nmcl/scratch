public class Grid
{
    public Grid (int maxX, int maxY, boolean debug)
    {
        _theGrid = new int[maxX][maxY];
        _debug = debug;

        for (int i = 0; i < maxX; i++)
        {
            for (int j = 0; j < maxY; j++)
                _theGrid[i][j] = 0;
        }
    }

    private int[][] _theGrid;
    private boolean _debug;
}