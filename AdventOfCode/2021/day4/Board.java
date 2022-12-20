public class Board
{
    public static final int MAX_X = 5;
    public static final int MAX_Y = 5;

    public Board (Integer[][] data)
    {
        _theBoard = new Cell[MAX_X][MAX_Y];

        for (int i = 0; i < MAX_X; i++)
        {
            for (int j = 0; j < MAX_Y; j++)
                _theBoard[i][j] = new Cell(data[i][j]);
        }
    }

    public final void call (int i)
    {
        for (int i = 0; i < MAX_X; i++)
        {
            for (int j = 0; j < MAX_Y; j++)
            {
                if (_theBoard[i][j].getValue() == i)
                    _theBoard[i][j].call();
            }
        }
    }

    public final boolean completed ()
    {
        for (int i = 0; i < MAX_X; i++)
        {
            for (int j = 0; j < MAX_Y; j++)
            {
                if (!_theBoard[i][j].called())
                    return false;
            }
        }

        return true;
    }

    private Cell[][] _theBoard;
}