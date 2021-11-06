public class PasswordPolicy
{
    public PasswordPolicy (int lower, int upper, char letter)
    {
        _lower = lower;
        _upper = upper;
        _letter = letter;
    }

    private int _lower;
    private int _upper;
    private char _letter;
}