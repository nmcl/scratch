/*
 * A cell in the image taken by the Camera.
 */

public class Cell
{
    public Cell (Coordinate coord, String contents, boolean debug)
    {
        _coord = coord;
        _contents = contents;
        _debug = debug;
    }

    public final String getContents ()
    {
        return _contents;
    }

    @Override
    public String toString ()
    {
        return "Cell at "+_coord+" is "+_contents;
    }

    private Coordinate _coord;
    private String _contents;
    private boolean _debug;
}