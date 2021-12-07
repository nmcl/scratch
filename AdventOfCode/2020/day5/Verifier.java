public class Verifier
{
    public final String PASS_1 = "FBFBBFFRLR";
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

        System.out.println("got "+s);

        return false;
    }
    
    private boolean _debug;
}