import java.util.*;

public class Packet
{
    public static final String EMPTY_PACKET = "-1";

    public Packet (LinkedList<String> outputs)
    {
        _address = Long.valueOf(outputs.poll());
        _x = Long.valueOf(outputs.poll());
        _y = Long.valueOf(outputs.poll());
    }

    public Packet (long address, long x, long y)
    {
        _address = address;
        _x = x;
        _y = y;
    }

    public final long getAddress ()
    {
        return _address;
    }

    public final long getX ()
    {
        return _x;
    }

    public final long getY ()
    {
        return _y;
    }

    @Override
    public String toString ()
    {
        return "< "+_address+", "+_x+", "+_y+" >";
    }

    private long _address;
    private long _x;
    private long _y;
}