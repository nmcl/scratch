import java.util.Objects;

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

    @Override
    public int hashCode ()
    {
        return Objects.hash(_name);
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;
        
        if (getClass() == obj.getClass())
        {
            Planet temp = (Planet) obj;

            return (_name.equals(temp._name));
        }

        return false;
    }

    @Override
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