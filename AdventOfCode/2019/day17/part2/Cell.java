/*
 * A cell in the image taken by the Camera.
 */

public class Cell
{
    public Cell (Coordinate coord, boolean isScaffold, boolean debug)
    {
        _coord = coord;
        _scaffold = isScaffold;
        _debug = debug;
    }

    @Override
    public String toString ()
    {
        return "Cell at "+_coord+" is "+((_scaffold) ? "scaffolding" : "not scaffolding");
    }

    private Coordinate _coord;
    private boolean _scaffold;
    private boolean _debug;
}