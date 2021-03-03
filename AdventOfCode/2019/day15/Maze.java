import java.util.*;

public class Maze
{
    public Maze ()
    {
        _map = new Vector<Tile>();
        _maxX = 0;
        _minX = 0;
        _maxY = 0;
        _minY = 0;
    }

    public final void addContent (Coordinate coord, String type)
    {
        // don't add if already present

        if (!_map.contains(coord))
            _map.add(new Tile(coord, type));
    }

    public final boolean visited (Coordinate coord)
    {
        return _map.contains(coord);
    }

    @Override
    public String toString ()
    {
        int x = _minX;
        int y = _minY;
        
        return "";
    }

    private Vector<Tile> _map;
    private int _maxX;
    private int _minX;
    private int _maxY;
    private int _minY;
}