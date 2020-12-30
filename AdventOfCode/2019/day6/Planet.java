import java.util.Objects;
import java.util.Vector;
import java.util.Enumeration;

public class Planet
{
    public Planet (String name)
    {
        _name = name;
        _satellites = new Vector<Planet>();
    }

    public String name ()
    {
        return _name;
    }
        
    public Vector<Planet> getSatellites ()
    {
        return _satellites;
    }

    public void addSatellite (Planet orbits)
    {
        _satellites.add(orbits);
    }

    public int getNumberOfSatellites ()
    {
        return _satellites.size();
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
        if ((_satellites != null) && (_satellites.size() > 0))
        {
            String str = "Planet "+_name+" has satellites: ";

            Enumeration<Planet> iter = _satellites.elements();

            while (iter.hasMoreElements())
            {
                Planet p = iter.nextElement();
                
                str += p.name()+" ";
            }

            return str;
        }
        else
            return "Planet "+_name;
    }

    private String _name;
    private Vector<Planet> _satellites;
}