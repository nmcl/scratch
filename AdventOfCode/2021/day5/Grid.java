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

    @Override
    public String toString ()
    {
        String str = "";

        for (int i = 0; i < maxX; i++)
        {
            for (int j = 0; j < maxY; j++)
            {
                if (_theGrid[i][j] == 0)
                    str += ".";
                else
                    str += _theGrid[i][j];
            }

            str += "\n";
        }

        return str;
    }

    private int[][] _theGrid;
    private boolean _debug;
}