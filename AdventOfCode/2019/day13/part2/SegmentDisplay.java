public class SegmentDisplay
{
    public SegmentDisplay ()
    {
        _score = 0;
    }

    public final int getScore ()
    {
        return _score;
    }

    public final void setScore (int s)
    {
        _score = s;
    }
    
    private int _score;
}