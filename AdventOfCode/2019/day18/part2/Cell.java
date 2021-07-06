public class Cell
{
    public Cell (Coordinate coord)
    {
        this(coord, CellId.OPEN_PASSAGE);
    }
    
    public Cell (Coordinate coord, char contents)
    {
        _coord = coord;
        _contents = contents;
    }
    
    public final char getContents ()
    {
        return _contents;
    }

    public void setContents (char c)
    {
        _contents = c;
    }

    public final Coordinate position ()
    {
        return _coord;
    }

    public boolean isEntrance ()
    {
        return (_contents == CellId.ENTRANCE);
    }

    public boolean isDoor ()
    {
        return Character.isUpperCase(_contents);
    }

    public boolean isKey ()
    {
        return Character.isLowerCase(_contents);
    }

    public boolean isWall ()
    {
        return (_contents == CellId.STONE_WALL);
    }

    public boolean isPassage ()
    {
        return (_contents == CellId.OPEN_PASSAGE);
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

            return temp.position().equals(position());  // we just compare on position not content
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
}