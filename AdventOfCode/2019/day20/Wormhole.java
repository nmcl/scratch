public class Wormhole
{
    public Wormhole (char first, char second, Coordinate location)
    {
        _name = "" + first + second;
        _position = location;
    }

    private String _name;
    private Coordinate _position;
}