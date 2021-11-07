public class PasswordPolicy
{
    public PasswordPolicy (int lower, int upper, char letter)
    {
        _lower = lower;
        _upper = upper;
        _letter = letter;
    }

    public int minumum ()
    {
        return _lower;
    }

    public int maximum ()
    {
        return _upper;
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

    private int _lower;
    private int _upper;
    private char _letter;
}