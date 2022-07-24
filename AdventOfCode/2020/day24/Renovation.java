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
        return null;
    }

    private boolean _debug;
}