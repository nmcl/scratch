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
        int index = 0;
        String remainder = line;

        while (index < line.length())
        {
            int directionSize = 2;

            if ((remainder.startsWith(Directions.EAST)) || (remainder.startsWith(Directions.WEST)))
                directionSize = 1;

            String direction = remainder.substring(0, directionSize);

            remainder = remainder.substring(directionSize);
            index += directionSize;

            if (_debug)
            {
                System.out.println("Direction: "+direction);
                System.out.println("Remainder: "+remainder);
            }

            coord = Directions.getCoordinate(direction, coord);
        }

        return coord;
    }

    private boolean _debug;
}