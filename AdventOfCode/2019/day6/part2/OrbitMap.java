import java.io.*;
import java.util.Vector;
import java.util.Enumeration;

public class OrbitMap
{
    public static final String TEST_DATA = "testdata.txt";
    public static final String ORBITAL_DATA = "data.txt";

    public static final String DELIMITER = "\\)";

    public static final String YOU = "YOU";
    public static final String SANTA = "SAN";
    
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
        Planet comPlanet = null;

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

                if ((comPlanet == null) && Planet.COM_NAME.equals(p1.name()))
                    comPlanet = p1;
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
            if (minimumOrbit(comPlanet, new Planet(YOU), new Planet(SANTA), thePlanets) == 4)
                System.out.println("Verified ok.");
            else
                System.out.println("Verify failed!");
        }
        else
            System.out.println("Minimum number of orbital transfers: "+minimumOrbit(comPlanet, new Planet(YOU), new Planet(SANTA), thePlanets));
    }

    private static int minimumOrbit (Planet com, Planet start, Planet end, Vector<Planet> solarSystem)
    {
        Planet beginPlanet = com.parentPlanet(start);
        Planet endPlanet = com.parentPlanet(end);

        if (_debug)
        {
            System.out.println("For "+start+" parent is "+beginPlanet.name());
            System.out.println("For "+end+" parent is "+endPlanet.name());
        }

        Vector<Planet> pathToStart = com.pathToSatellite(start);
        Vector<Planet> pathToEnd  = com.pathToSatellite(end);

        if (_debug)
        {
            System.out.println("Path to start: "+pathToStart);
            System.out.println("\nPath to end: "+pathToEnd);
        }

        // Now find common root.

        Enumeration<Planet> iter = pathToStart.elements();
        Planet commonRoot = null;  // assume just one?!
        boolean endOfTheRoad = false;

        while (iter.hasMoreElements() && !endOfTheRoad)
        {
            Planet p = iter.nextElement();

            if (pathToEnd.contains(p))
                commonRoot = p;
            else
                endOfTheRoad = true;
        }

        if (_debug)
            System.out.println("Common root: "+commonRoot.name());

        int hopsFromStartToCommon = commonRoot.hopsToSatellite(start) -1;
        int hopsFromCommonToEnd = commonRoot.hopsToSatellite(end) -1;

        if (_debug)
        {
            System.out.println("Transfers from start to common: "+(hopsFromStartToCommon -1));
            System.out.println("Transfers from common to end: "+(hopsFromCommonToEnd -1));
        }

        return hopsFromStartToCommon + hopsFromCommonToEnd;
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