public class Moon
{
    public Moon (int x, int y, int z)
    {
        _position = new Coordinate(x, y, z);
        _velocity = new Velocity();  // the x, y, and z velocity of each moon starts at 0.
    }

    private Coordinate _position;
    private Velocity _velocity;
}