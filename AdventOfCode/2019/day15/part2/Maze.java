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
        _oxygenStation = null;
        _startingPoint = null;
    }

    public final void updateTile (Coordinate coord, String type)
    {
        Tile t = new Tile(coord, type);
        int index = _map.indexOf(t);
        
        if ((_startingPoint == null) && (TileId.STARTING_POINT.equals(type)))
            _startingPoint = coord;
        else
        {
            if ((_oxygenStation == null) && (TileId.OXYGEN_STATION.equals(type)))
                _oxygenStation = coord;
        }

        if (index == -1)
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

            _map.add(t);
        }
        else
            _map.set(index, t);
    }

    public final Coordinate getStartingPoint ()
    {
        return _startingPoint;
    }

    public final Coordinate getOxygenStation ()
    {
        return _oxygenStation;
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

    public final int[] getDimensons ()
    {
        int[] dims = new int[4];

        dims[0] = _minX;
        dims[1] = _maxX;
        dims[2] = _minY;
        dims[3] = _maxY;

        return dims;
    }

    @Override
    public String toString ()
    {
        String str = "--------\n";

        for (int y = _minY; y <= _maxY; y++)
        {
            for (int x = _minX; x <= _maxX; x++)
                str += tileType(new Coordinate(x, y));

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
    private Coordinate _oxygenStation;
    private Coordinate _startingPoint;
}