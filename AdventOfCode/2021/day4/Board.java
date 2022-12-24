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

    public final void call (int value)
    {
        for (int i = 0; i < MAX_X; i++)
        {
            for (int j = 0; j < MAX_Y; j++)
            {
                if (_theBoard[i][j].getValue() == value)
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

    @Override
    public String toString ()
    {
        String str = "";

        for (int i = 0; i < Board.MAX_X; i++)
        {
            for (int j = 0; j < Board.MAX_Y; j++)
            {
                int val = _theBoard[i][j].getValue();

                if (val < 10)
                    str += " ";
                    
                str += _theBoard[i][j].getValue()+" ";
            }

            str += "\n";
        }

        return str;
    }

    private Cell[][] _theBoard;
}