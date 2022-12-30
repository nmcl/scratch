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

    public int overlappingLines ()
    {
        int overlaps = 0;

        for (int i = 0; i < _theGrid.length; i++)
        {
            for (int j = 0; j < _theGrid[0].length; j++)
            {
                if (_theGrid[i][j] > 1)
                    overlaps++;
            }
        }

        return overlaps;
    }

    // only horizontal or vertical lines for part 1

    public void plot (Coordinate start, Coordinate finish)
    {
        if (start.getX() == finish.getX())
        {
            int startY = ((start.getY() < finish.getY() ? start.getY() : finish.getY()));
            int finishY = ((start.getY() > finish.getY() ? start.getY() : finish.getY()));

            for (int y = startY; y <= finishY; y++)
            {
                _theGrid[y][start.getX()]++;
            }
        }
        else
        {
            int startX = ((start.getX() < finish.getX() ? start.getX() : finish.getX()));
            int finishX = ((start.getX() > finish.getX() ? start.getX() : finish.getX()));

            for (int x = startX; x <= finishX; x++)
            {
                _theGrid[start.getY()][x]++;
            }
        }
    }

    @Override
    public String toString ()
    {
        String str = "";

        for (int i = 0; i < _theGrid.length; i++)
        {
            for (int j = 0; j < _theGrid[0].length; j++)
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