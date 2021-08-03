/*
 * A Portal is two letters but as we scan in the information about
 * the Donut and Portals we only get to read one letter at a time.
 * A SemiPortal is half of a Portal representation which we use temporarily
 * while reading in the data.
 */

public class SemiPortal extends Tile
{
    public static final String START = "AA";
    public static final String EXIT = "ZZ";

    public SemiPortal(Coordinate position)
    {
        this(position, (char) Character.UNASSIGNED);
    }

    public SemiPortal(Coordinate position, char portalId)
    {
        super(position, TileId.PORTAL);

        _portalId = portalId;
        _portalName = "*" + portalId + "*"; // a default name initially.
    }

    public final char getId()
    {
        return _portalId;
    }

    public final String getName()
    {
        return _portalName;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;

        if ((getClass() == obj.getClass()) || (super.getClass() == obj.getClass()))
        {
            Tile temp = (Tile) obj;

            return _position.equals(temp._position); // only compare position not type.
        }

        return false;
    }

    private char _portalId;
    private String _portalName;
}
