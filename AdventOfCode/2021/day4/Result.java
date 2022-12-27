public class Result
{
    public Result (Board b, int number)
    {
        _board = b;
        _number = number;
    }

    public int getResult ()
    {
        int result = 0;


        return -1;
    }

    public final int sumOfUnmarked ()
    {
        return _board.sumOfUnmarked();
    }

    public final int getLastNumberCalled ()
    {
        return _number;
    }

    @Override
    public String toString ()
    {
       return _board.toString() + "\n"+_number;
    }

    private Board _board;
    private int _number;
}