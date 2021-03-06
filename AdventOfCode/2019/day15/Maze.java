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
        System.out.println("**adding content "+type+" at location "+coord);

        // don't add if already present

        if (!_map.contains(coord))
        {
            if (coord.getX() > _maxX)
                _maxX = coord.getX();
            else
            {
                if (coord.getX() < _minX)
                    _minX = coord.getX();
            }

            if (coord.getY() > _maxY)
                _maxY = coord.getY();
            else
            {
                if (coord.getY() < _minY)
                    _minY = coord.getY();
            }

            _map.add(new Tile(coord, type));
        }
    }

    public final boolean isWall (Coordinate coord)
    {
        return tileType(coord).equals(TileId.WALL);
    }

    public final boolean isUnexplored (Coordinate coord)
    {
        return tileType(coord).equals(TileId.UNEXPLORED);
    }
    
    public final boolean isExplored (Coordinate coord)
    {
        return !isUnexplored(coord);
    }

    public final boolean isOxygenStation (Coordinate coord)
    {
        return tileType(coord).equals(TileId.OXYGEN_STATION);
    }

    public final String tileType (Coordinate coord)
    {
        int index = _map.indexOf(new Tile(coord));

        if (index != -1)
        {
            Tile t = _map.get(index);

            return t.content();
        }
        else
            return TileId.UNEXPLORED;
    }

    public final boolean visited (Coordinate coord)
    {
        return _map.contains(coord);
    }

    @Override
    public String toString ()
    {
        String str = "--------\n";

        for (int y = _minY; y < _maxY; y++)
        {
            for (int x = _minX; x < _maxX; x++)
            {
                Tile theTile = new Tile(new Coordinate(x, y));
                int index = _map.indexOf(theTile);

                if (index != -1)
                {
                    theTile = _map.get(index);
                    str += theTile.content();
                }
                else
                    str += TileId.UNEXPLORED;
            }

            str += "\n";
        }

        str += "--------\n";
        
        return str;
    }

    private Vector<Tile> _map;
    private int _maxX;
    private int _minX;
    private int _maxY;
    private int _minY;
}