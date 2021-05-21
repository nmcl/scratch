/*
 * A cell in the image taken by the Camera.
 */

public class Cell
{
    public Cell (Coordinate coord, char contents, boolean debug)
    {
        _coord = coord;
        _contents = contents;
        _debug = debug;
    }

    public Cell (Coordinate coord)
    {
        this(coord, CellId.OPEN_PASSAGE, false); // some defaults
    }

    public final char getContents ()
    {
        return _contents;
    }

    public final void setContents (char contents)
    {
        _contents = contents;
    }
    
    public final Coordinate position ()
    {
        return _coord;
    }

    @Override
    public int hashCode ()
    {
        return _coord.hashCode();
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;
        
        if (getClass() == obj.getClass())
        {
            Cell temp = (Cell) obj;

            return temp.position().equals(position());
        }

        return false;
    }

    @Override
    public String toString ()
    {
        return "Cell at "+_coord+" is "+_contents;
    }

    private Coordinate _coord;
    private char _contents;
    private boolean _debug;
}