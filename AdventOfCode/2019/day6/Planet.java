public class Planet
{
    public Planet (String name)
    {
        this(name, null);
    }

    public Planet (String name, String orbits)
    {
        _name = name;

        if (orbits != null)
            _orbits = new Planet(orbits);
        else
            _orbits = null;
    }

    public String name ()
    {
        return _name;
    }
        
    public Planet getOrbit ()
    {
        return _orbits;
    }

    public void setOrbit (Planet orbits)
    {
        _orbits = orbits;
    }

    public String toString ()
    {
        if (_orbits != null)
            return "Plant "+_name+" orbits "+_orbits.name();
        else
            return "Plant "+_name+" is a wanderer!";
    }

    private String _name;
    private Planet _orbits;
}