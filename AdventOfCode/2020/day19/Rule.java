public class Rule
{
    public Rule (char name)
    {
        _name = name;
    }

    public final char getName ()
    {
        return _name;
    }

    private char _name;
}