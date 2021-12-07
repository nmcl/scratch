public class Verifier
{
    public final String PASS_1 = "FBFBBFFRLR";
    public final Seat PASS_1_SEAT = new Seat(44, 5, 357);
    public final String PASS_2 = "BFFFBBFRRR";
    public final Seat PASS_2_SEAT = new Seat(70, 7, 567);
    public final String PASS_3 = "FFFBBBFRRR";
    public final Seat PASS_3_SEAT = new Seat(14, 7, 119);
    public final String PASS_4 = "BBFFBBFRLL";
    public final Seat PASS_4_SEAT = new Seat(102, 4, 820);

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
            code = new Barcode(PASS_2, _debug);

            s = code.getSeat();

            if (PASS_2_SEAT.equals(s))
            {
                code = new Barcode(PASS_3, _debug);

                s = code.getSeat();

                if (PASS_3_SEAT.equals(s))
                {
                    code = new Barcode(PASS_4, _debug);

                    s = code.getSeat();

                    if (PASS_4_SEAT.equals(s))
                        return true;
                }
            }
        }

        return false;
    }
    
    private boolean _debug;
}