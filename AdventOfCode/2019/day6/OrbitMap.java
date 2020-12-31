import java.io.*;
import java.util.Vector;
import java.util.Enumeration;

public class OrbitMap
{
    public static final String TEST_DATA = "testdata.txt";
    public static final String ORBITAL_DATA = "data.txt";

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
                Planet sat = new Planet(values[1]);
                int existingPlanet = thePlanets.indexOf(p1);
                int existingSat = thePlanets.indexOf(sat);

                if (existingPlanet != -1)
                    p1 = thePlanets.elementAt(existingPlanet);
                else
                    thePlanets.add(p1);

                if (existingSat != -1)
                    sat = thePlanets.elementAt(existingSat);
                else
                    thePlanets.add(sat);
                
                if (_debug)
                    System.out.println("Adding satellite "+sat+" to "+p1);

                p1.addSatellite(sat);
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
            int orbits = totalOrbits(thePlanets);

            System.out.println("Total orbits: "+orbits);

            if (orbits == 42)
                System.out.println("Verified ok.");
            else
                System.out.println("Verify failed!");
        }
    }

    private static void printPlanets (Vector<Planet> solarSystem)
    {
        Enumeration<Planet> iter = solarSystem.elements();

        while (iter.hasMoreElements())
        {
            Planet thePlanet = iter.nextElement();

            System.out.println(thePlanet.printAll());
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

    private static int totalOrbits (Vector<Planet> solarSystem)
    {
        int number = 0;
        Enumeration<Planet> iter = solarSystem.elements();

        while (iter.hasMoreElements())
        {
            Planet thePlanet = iter.nextElement();
            
            if (_debug)
                System.out.println("Calculating orbit for "+thePlanet);

            number += thePlanet.totalOrbits();
        }

        if (_debug)
            System.out.println("We have "+number+" orbits.");

        return number;
    }

    private static boolean _debug = false;
}