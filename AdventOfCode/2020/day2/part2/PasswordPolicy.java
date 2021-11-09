public class PasswordPolicy
{
    public PasswordPolicy (int first, int second, char letter)
    {
        _first = first;
        _second = second;
        _letter = letter;
    }

    public int first ()
    {
        return _first;
    }

    public int second ()
    {
        return _second;
    }

    public char letter ()
    {
        return _letter;
    }

    public boolean valid ()
    {
        if ((_first > 0) && (_second > 0))
            return true;
        else
            return false;
    }

    @Override
    public String toString ()
    {
        return "PasswordPolicy: < "+_first+", "+_second+", "+_letter+" >";
    }

    private int _first;
    private int _second;
    private char _letter;
}