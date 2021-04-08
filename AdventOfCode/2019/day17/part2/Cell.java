/*
 * A cell in the image taken by the Camera.
 */

public class Cell
{
    public Cell (Coordinate coord, String content, boolean debug)
    {
        _coord = coord;
        _content = content;
        _debug = debug;
    }

    @Override
    public String toString ()
    {
        return "Cell at "+_coord+" is "+((_scaffold) ? "scaffolding" : "not scaffolding");
    }

    private Coordinate _coord;
    private String _content
    private boolean _debug;
}