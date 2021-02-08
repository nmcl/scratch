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

    @Override
    public String toString ()
    {
        return "Score: "+_score;
    }
    
    private int _score;
}