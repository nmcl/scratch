import java.io.*;
import java.util.Vector;
import java.util.Enumeration;

public class OrbitMap
{
    public static final String TEST_DATA = "testdata.txt";
    public static final String ORBITAL_DATA = "data.txt";

    public static final String COM_NAME = "COM";

    public static final String DELIMITER = "\\)";
    public static void main (String[] args)
    {
        boolean runVerify = false;
        String dataFile = ORBITAL_DATA;
        boolean dumpInput = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-help] [-verify] [-dump] [-debug]");
                System.exit(0);
            }

            if ("-verify".equals(args[i]))
            {
                runVerify = true;
                dataFile = TEST_DATA;
            }

            if ("-dump".equals(args[i]))
                dumpInput = true;

            if ("-debug".equals(args[i]))
                _debug = true;
        }

        Vector<Planet> thePlanets = new Vector<Planet>();
        BufferedReader reader = null;

        try
        {
            File file = new File(dataFile);
            String line = null;
            String values[] = null;

            reader = new BufferedReader(new FileReader(file));

            while ((line = reader.readLine()) != null)
            {
                values = line.split(DELIMITER);

                Planet p1 = new Planet(values[0]);
                int existingPlanet = thePlanets.indexOf(p1);

                if (_debug)
                    System.out.println("Checking to see if "+p1+" exists, "+((existingPlanet == -1) ? "which it doesn't." : "which it does."));

                if (existingPlanet != -1)
                {
                    Planet p2 = thePlanets.elementAt(existingPlanet);

                    if (_debug)
                        System.out.println("Adding satellite "+values[1]+" to "+p2.name());

                    p2.addSatellite(new Planet(values[1]));
                }
                else
                {
                    if (_debug)
                        System.out.println("Adding "+p1+" with satellite "+values[1]);

                    p1.addSatellite(new Planet(values[1]));
                    thePlanets.add(p1);
                }
            }
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (Throwable ex)
            {
                ex.printStackTrace();
            }
        }
    
        if (dumpInput)
        {
            printPlanets(thePlanets);

            System.exit(0);
        }

        if (runVerify)
        {
            int directOrbits = totalPlanets(thePlanets);
            int indirects = indirectOrbits(thePlanets);
        }
    }

    private static void printPlanets (Vector<Planet> solarSystem)
    {
        Enumeration<Planet> iter = solarSystem.elements();

        while (iter.hasMoreElements())
        {
            Planet thePlanet = iter.nextElement();

            System.out.println(thePlanet);
        }
    }

    /*
     * Find the total number of unique planets.
     */

    private static int totalPlanets (Vector<Planet> solarSystem)
    {
        int total = 0;
        Enumeration<Planet> iter = solarSystem.elements();
        Vector<Planet> planets = new Vector<Planet>();

        while (iter.hasMoreElements())
        {
            Planet thePlanet = iter.nextElement();
            Vector<Planet> satellites = thePlanet.getSatellites();

            if (satellites.size() > 0)
            {
                Enumeration<Planet> e = satellites.elements();

                while (e.hasMoreElements())
                {
                    Planet theSatellite = e.nextElement();

                    if (!planets.contains(theSatellite))
                    {
                        total++;

                        planets.add(theSatellite);
                    }
                }
            }
        }

        if (_debug)
            System.out.println("Total number of unique planets: "+total);
            
        return total;
    }

    private static int indirectOrbits (Vector<Planet> solarSystem)
    {
        int number = 0;
        Enumeration<Planet> iter = solarSystem.elements();
        Vector<String> planetNames = new Vector<String>();

        while (iter.hasMoreElements())
        {
            Planet thePlanet = iter.nextElement();
            
            if (_debug)
                System.out.println("Checking to see if planet "+thePlanet.name()+" is unique: "+(!planetNames.contains(thePlanet.name())));

            if (!planetNames.contains(thePlanet.name()))
                planetNames.add(thePlanet.name());
        }

        if (_debug)
            System.out.println("We have "+number+" planets.");

        return number;
    }

    private static boolean _debug = false;
}