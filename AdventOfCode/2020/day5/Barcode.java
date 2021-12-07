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
            int startRange = 1;
            int endRange = 128;

            for (int i = 0; i < _data.length(); i++)
            {
                switch (_data.charAt(i))
                {
                    case FRONT:
                    {
                        endRange = endRange / 2;
                    }
                    break;
                    case BACK:
                    {
                        startRange = endRange / 2;
                    }
                    break;

                }
            }
        }

        return _theSeat;
    }

    private String _data;
    private Seat _theSeat;
    private boolean _debug;
}