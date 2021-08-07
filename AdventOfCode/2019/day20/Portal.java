/*
 * A Portal is two letters but as we scan in the information about
 * the Donut and Portals we only get to read one letter at a time.
 * A SemiPortal is half of a Portal representation which we use temporarily
 * while reading in the data. Once we've read in the entire Maze, we do
 * some processing and will replace the SemiPortal with a Portal instance.
 */

public class Portal extends Tile
{
    public Portal(Coordinate position)
    {
        this(position, (char) Character.UNASSIGNED);
    }

    public Portal(Coordinate position, char portalId)
    {
        super(position, TileId.PORTAL);

        _portalId = portalId;
    }

    public final char getId()
    {
        return _portalId;
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
}
