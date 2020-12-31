import java.util.Objects;
import java.util.Vector;
import java.util.Enumeration;

public class Planet
{
    public static final String COM_NAME = "COM";

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

    public boolean hasSatellite (Planet satellite)
    {
        return _satellites.contains(satellite);
    }

    public void addSatellite (Planet orbits)
    {
        _satellites.add(orbits);
    }

    public int getNumberOfSatellites ()
    {
        return _satellites.size();
    }

    public Planet parentPlanet (Planet satellite)
    {
        if ((_satellites != null) && (_satellites.size() > 0))
        {
            if (_satellites.contains(satellite))
                return this;
            else
            {
                Enumeration<Planet> iter = _satellites.elements();

                while (iter.hasMoreElements())
                {
                    Planet p = iter.nextElement();
                    Planet parent = p.parentPlanet(satellite);

                    if (parent != null)
                        return parent;
                }
            }
        }

        return null;
    }

    public Vector<Planet> pathToSatellite (Planet satellite)
    {
        Vector<Planet> path = new Vector<Planet>();
        boolean found = false;

        if ((_satellites != null) && (_satellites.size() > 0))
        {
            if (_satellites.contains(satellite))
            {
                path.add(this);

                found = true;
            }
            else
            {
                path.add(this);

                Enumeration<Planet> iter = _satellites.elements();

                while (iter.hasMoreElements())
                {
                    Planet p = iter.nextElement();
                    Vector<Planet> subPath = p.pathToSatellite(satellite);

                    if ((subPath != null) && (subPath.size() > 0))
                    {
                        path.addAll(subPath);
                        found = true;
                    }
                }
            }
        }

        if (found)
            return path;
        else
            return null;
    }

    public int totalOrbits ()
    {
        int total = 0;
        
        if ((_satellites != null) && (_satellites.size() > 0))
        {
            Enumeration<Planet> iter = _satellites.elements();

            while (iter.hasMoreElements())
            {
                Planet p = iter.nextElement();

                total += p.totalOrbits();
                total++;
            }
        }

        return total;
    }

    public String printAll ()
    {
        if ((_satellites != null) && (_satellites.size() > 0))
        {
            String str = "Planet "+_name+" has satellites: ";

            Enumeration<Planet> iter = _satellites.elements();

            while (iter.hasMoreElements())
            {
                Planet p = iter.nextElement();
                
                str += p.printAll()+" ";
            }

            return str;
        }
        else
            return "Planet "+_name+".";
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