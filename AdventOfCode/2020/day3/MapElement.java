public class MapElement
{
    public static final char TREE = '#';
    public static final char OPEN = '.';
    
    public MapElement (Coordinate coord, char type)
    {
        _coord = coord;
        _type = type;
    }

    public MapElement (MapElement copy)
    {
        _coord = new Coordinate(copy.position().getX(), copy.position().getY());
        _type = copy.type();
    }

    public final Coordinate position ()
    {
        return _coord;
    }

    public final char type ()
    {
        return _type;
    }

    private Coordinate _coord;
    private char _type;
}