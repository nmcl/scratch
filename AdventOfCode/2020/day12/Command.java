public class Command
{
    public Command (char type)
    {
        _type = type;
    }

    public final char action ()
    {
        return _type;
    }
    
    private char _type;
}