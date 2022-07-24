import java.util.*;

public class Renovation
{
    public Renovation (boolean debug)
    {
        _debug = debug;
    }

    public Vector<Coordinate> getBlackTiles (Vector<String> lines)
    {
        Vector<Coordinate> blackTiles = new Vector<Coordinate>();

        for (int i = 0; i < lines.size(); i++)
        {
            Coordinate coord = tilePosition(lines.elementAt(i));

            if (!blackTiles.contains(coord))
                blackTiles.add(coord);
            else
                blackTiles.remove(coord);
        }

        return blackTiles;
    }

    private Coordinate tilePosition (String line)
    {
        Coordinate coord = new Coordinate(0, 0);
        
        for (int i = 0; i < line.length(); i++)
        {
            int directionSize = 2;

            if ((line.charAt(i) == Directions.EAST_CHAR) || (line.charAt(i) == Directions.WEST_CHAR))
                directionSize = 1;

            String direction = line.substring(i, directionSize);

            i += directionSize;

            if (_debug)
                System.out.println("Direction: "+direction);

            coord = Directions.getCoordinate(direction, coord);
        }

        return coord;
    }

    private boolean _debug;
}