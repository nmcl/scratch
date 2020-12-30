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
                thePlanets.add(new Planet(values[1], values[0]));
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
            }
        }
    
        if (dumpInput)
        {
            printPlanets(thePlanets);

            System.exit(0);
        }

        if (runVerify)
        {
            int directOrbits = thePlanets.size();
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
            System.out.println("We have "+planetNames.size()+" planets.");

        return number;
    }

    private static boolean _debug = false;
}