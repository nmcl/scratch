/*
 * A cell in the image taken by the Camera.
 */

public class Cell
{
    public Cell (Coordinate coord, String cellType, boolean debug)
    {
        _coord = coord;
        _scaffold = CellId.SCAFFOLDING.equals(cellType);
        _debug = debug;
    }

    private Coordinate _coord;
    private boolean _scaffold;
    private boolean _debug;
}