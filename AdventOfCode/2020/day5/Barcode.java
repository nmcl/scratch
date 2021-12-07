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
            int startRow = 0;
            int endRow = 127;
            int startColumn = 0;
            int endColumn = 7;

            for (int i = 0; i < _data.length(); i++)
            {
                switch (_data.charAt(i))
                {
                    case FRONT:
                    {
                        // F means to take the lower half

                        if (_debug)
                            System.out.println("Front currently: < "+startRow+", "+endRow+" >");

                        int size = endRow - startRow;

                        endRow = endRow - (size / 2) -1;

                        if (_debug)
                            System.out.println("Front now: < "+startRow+", "+endRow+" >");
                    }
                    break;
                    case BACK:
                    {
                        // B means to take the upper half

                        if (_debug)
                            System.out.println("Back currently: < "+startRow+", "+endRow+" >");

                        int size = endRow - startRow;

                        startRow = startRow + (size / 2) +1;

                        if (_debug)
                            System.out.println("Back now: < "+startRow+", "+endRow+" >");
                    }
                    break;
                    case LEFT:
                    {
                        // L means to keep the lower half

                        if (_debug)
                            System.out.println("Left currently: < "+startColumn+", "+endColumn+" >");

                        int size = endColumn - startColumn;

                        endColumn = endColumn - (size / 2) -1;

                        if (_debug)
                            System.out.println("Left now: < "+startColumn+", "+endColumn+" >");
                    }
                    break;
                    case RIGHT:
                    {
                        // R means to keep the upper half

                        if (_debug)
                            System.out.println("Right currently: < "+startColumn+", "+endColumn+" >");

                        int size = endColumn - startColumn;

                        startColumn = startColumn + (size / 2) +1;

                        if (_debug)
                            System.out.println("Right now: < "+startColumn+", "+endColumn+" >");
                    }
                    break;
                    default:
                    {
                        System.out.println("Unrecognised barcode character: "+_data.charAt(i));
                    }
                    break;
                }
            }

            System.out.println(startRow+" "+startColumn);
            
            _theSeat = new Seat(startRow, startColumn);
        }

        return _theSeat;
    }

    private String _data;
    private Seat _theSeat;
    private boolean _debug;
}