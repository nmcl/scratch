public class Crab
{
    public Crab (int h_pos)
    {
        _h_pos = h_pos;
    }

    public final int horizontalPosition ()
    {
        return _h_pos;
    }

    private int _h_pos;
}