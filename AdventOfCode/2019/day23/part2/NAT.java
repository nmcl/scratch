public class NAT
{
    public static final long ADDRESS = 255;

    public NAT ()
    {
        _packet = null;
    }

    public final void setPacket (Packet p)
    {
        _packet = p;
    }

    public final Packet getPacket ()
    {
        return _packet;
    }

    private Packet _packet;
}