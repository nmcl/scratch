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

        _completed = false;
        _debug = debug;
    }

    public final boolean completed ()
    {
        return _completed;
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

    public final int sumOfUnmarked ()
    {
        int val = 0;

        for (int i = 0; i < MAX_X; i++)
        {
            for (int j = 0; j < MAX_Y; j++)
            {
                if (!_theBoard[i][j].called())
                    val += _theBoard[i][j].getValue();
            }
        }

        return val;
    }

    public final boolean completeLine ()
    {
        _completed = true;

        for (int i = 0; i < MAX_X; i++)
        {
            boolean lineComplete = true;

            for (int j = 0; j < MAX_Y; j++)
            {
                if (_debug)
                    System.out.println("Checking horizonal "+_theBoard[i][j].getValue()+" and "+_theBoard[i][j].called());

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
                if (_debug)
                    System.out.println("Checking vertical "+_theBoard[i][j].getValue()+" and "+_theBoard[i][j].called());

                if (!_theBoard[j][i].called())
                    lineComplete = false;
            }

            if (lineComplete)
                return true;
        }

        _completed = false;

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
    private boolean _completed;
    private boolean _debug;
}