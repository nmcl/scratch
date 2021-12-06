public class Barcode
{
    public Barcode (String entry, boolean debug)
    {
        _data = entry;
        _theSeat = null;
        _debug = debug;
    }

    public final Seat getSeat ()
    {
        if (_theSeat == null)
        {

        }

        return _theSeat;
    }
    
    private String _data;
    private Seat _theSeat;
    private boolean _debug;
}