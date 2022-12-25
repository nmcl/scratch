public class Board
{
    public static final int MAX_X = 5;
    public static final int MAX_Y = 5;

    public Board (Integer[][] data, boolean debug)
    {
        _theBoard = new Cell[MAX_X][MAX_Y];

        for (int i = 0; i < MAX_X; i++)
        {
            for (int j = 0; j < MAX_Y; j++)
                _theBoard[i][j] = new Cell(data[i][j]);
        }

        _debug = debug;
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

    public final boolean completeLine ()
    {
        for (int i = 0; i < MAX_X; i++)
        {
            boolean lineComplete = true;

            for (int j = 0; j < MAX_Y; j++)
            {
                if (_debug)
                    System.out.println("Checking "+_theBoard[i][j].getValue()+" and "+_theBoard[i][j].called());

                if (!_theBoard[i][j].called())
                    lineComplete = false;
            }

            if (lineComplete)
                return true;
        }

        for (int i = 0; i < MAX_X; i++)
        {
            boolean lineComplete = true;

            for (int j = 0; j < MAX_Y; j++)
            {
                if (!_theBoard[j][i].called())
                    lineComplete = false;
            }

            if (lineComplete)
                return true;
        }

        return false;
    }

    @Override
    public String toString ()
    {
        String str = "";

        for (int i = 0; i < Board.MAX_X; i++)
        {
            for (int j = 0; j < Board.MAX_Y; j++)
                str += _theBoard[i][j] +" ";

            str += "\n";
        }

        return str;
    }

    private Cell[][] _theBoard;
    private boolean _debug;
}