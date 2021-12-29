public class Command
{
    public Command (char type, int distance)
    {
        _type = type;
        _distance = distance;
    }

    public final char action ()
    {
        return _type;
    }

    public final int distance ()
    {
        return _distance;
    }

    @Override
    public String toString ()
    {
        return "Command < "+_type+", "+_distance+" >";
    }

    private char _type;
    private int _distance;
}