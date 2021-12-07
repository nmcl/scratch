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
            int startRow = 1;
            int endRow = 128;
            int startColumn = 1;
            int endColumn = 8;

            for (int i = 0; i < _data.length(); i++)
            {
                switch (_data.charAt(i))
                {
                    case FRONT:
                    {
                        // F means to take the lower half

                        int size = endRange - startRange;

                        endRange = startRange + (size / 2);
                    }
                    break;
                    case BACK:
                    {
                        // B means to take the upper half

                        int size = endRange - startRange;

                        startRange = startRange + (size / 2);
                    }
                    break;
                    case LEFT:
                    {
                        // L means to keep the lower half
                    }
                    break;
                    case RIGHT:
                    {
                        // R means to keep the upper half
                    }
                    break;
                    default:
                    {
                        System.out.println("Unrecognised barcode character: "+_data.charAt(i));
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