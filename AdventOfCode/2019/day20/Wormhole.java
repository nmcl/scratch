/*
 * A wormhole represents the connection between two Portals.
 */

public class Wormhole
{
    public Wormhole (Tile first, Tile second, Coordinate location)
    {
        _name = "" + ((Portal) first).getId() + ((Portal) second).getId();
        _position = location;
    }

    private String _name;
    private Coordinate _position;
}