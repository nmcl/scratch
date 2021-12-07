public class Verifier
{
    public final String PASS_1 = "FBFBBFFRLR";
    public final Seat PASS_1_SEAT = new Seat(44, 5, 357);
    public final String PASS_2 = "BFFFBBFRRR";
    public final String PASS_3 = "FFFBBBFRRR";
    public final String PASS_4 = "BBFFBBFRLL";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Barcode code = new Barcode(PASS_1, _debug);
        Seat s = code.getSeat();

        if (PASS_1_SEAT.equals(s))
        {
            return true;
        }

        return false;
    }
    
    private boolean _debug;
}