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

    @Override
    public String toString ()
    {
        return "PasswordPolicy: < "+_lower+", "+_upper+", "+_letter+" >";
    }

    private int _first;
    private int _second;
    private char _letter;
}