public class Result
{
    public Result (Board b, int number)
    {
        _board = b;
        _number = number;
    }

    private int getResult ()
    {
        return -1;
    }

    @Override
    public String toString ()
    {
       return _board.toString() + "\n"+_number;
    }

    private Board _board;
    private int _number;
}