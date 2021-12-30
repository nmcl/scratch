public class Bus
{
    // https://en.wikipedia.org/wiki/Chinese_remainder_theorem

    public Bus (long id, long index)
    {
        _id = id;
        _remainder = -index;

        System.out.println("initial rem "+_remainder);

        while (_remainder < 0)
        {
            _remainder += _id;
        }

        System.out.println("rem now "+_remainder);
    }

    public final long getRemainder ()
    {
        return _remainder;
    }

    // in this theorem implementation, id == mod
    
    public final long getID ()
    {
        return _id;
    }

    @Override
    public String toString ()
    {
        return "Bus "+_id+" remainder "+_remainder;
    }

    private long _id;
    private long _remainder;
}