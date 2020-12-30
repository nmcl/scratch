public class Planet
{
    public Planet (String name)
    {
        super(name, null);
    }

    public Planet (String name, Planet orbits)
    {
        _name = name;
        _orbits = orbits;
    }

    public Planet getOrbit ()
    {
        return _orbits;
    }

    public void setOrbit (Planet orbits)
    {
        _orbits = orbits;
    }
    
    private String _name;
    private Planet _orbits;
}