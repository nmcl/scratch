public class Bus
{
    public Bus (int id)
    {
        _id = id;
    }

    public final int getID ()
    {
        return _id;
    }

    @Override
    public String toString ()
    {
        return "Bus: "+_id;
    }

    private int _id;
}