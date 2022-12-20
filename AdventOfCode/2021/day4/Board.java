public class Board
{
    public static final int MAX_X = 5;
    public static final int MAX_Y = 5;

    public Board ()
    {
        _theBoard = new Cell[MAX_X][MAX_Y];

        for (int i = 0; i < MAX_X; i++)
        {
            for (int j = 0; j < MAX_Y; j++)
                _theBoard[i][j] = null;
        }
    }

    private Cell[][] _theBoard;
}