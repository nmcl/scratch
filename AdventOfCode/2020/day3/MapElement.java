public class MapElement
{
    public static final char TREE = '#';
    public static final char OPEN = '.';
    
    public MapElement (Coordinate coord, char type)
    {
        _coord = coord;
        _type = type;
    }

    private Coordinate _coord;
    private char _type;
}