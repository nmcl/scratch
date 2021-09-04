public class Packet
{
    public Packet (int address, long x, long y)
    {
        _address = address;
        _x = x;
        _y = y;
    }

    public final int getAddress ()
    {
        return _address;
    }

    private final long getX ()
    {
        return _x;
    }

    private final long getY ()
    {
        return _y;
    }

    private int _address;
    private long _x;
    private long _y;
}