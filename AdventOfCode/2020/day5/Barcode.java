public class Barcode
{
    public static final char FRONT = 'F';
    public static final char BACK = 'B';
    public static final char LEFT = 'L';
    public static final char RIGHT = 'R';

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
            int range = 127;

            for (int i = 0; i < _data.length(); i++)
            {

            }
        }

        return _theSeat;
    }

    private String _data;
    private Seat _theSeat;
    private boolean _debug;
}