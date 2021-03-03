import java.util.*;

public class Maze
{
    public Maze ()
    {
        _map = new Vector<Tile>();
    }

    public final void addContent (Coordinate coord, String type)
    {
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
        return "";
    }

    private Vector<Tile> _map;
}