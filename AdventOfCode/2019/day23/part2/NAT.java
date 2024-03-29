public class NAT
{
    public static final long ADDRESS = 255;

    public NAT ()
    {
        _packet = new Packet();
    }

    public final boolean isEmpty ()
    {
        return _packet.isEmpty();
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