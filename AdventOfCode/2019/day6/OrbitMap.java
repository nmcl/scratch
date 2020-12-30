import java.io.*;
import java.util.Stack;

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
                System.out.println("Usage: [-help] [-verify] [-dump]");
                System.exit(0);
            }

            if ("-verify".equals(args[i]))
            {
                runVerify = true;
                dataFile = TEST_DATA;
            }

            if ("-dump".equals(args[i]))
                dumpInput = true;
        }

        Stack<Planet> thePlanets = new Stack<Planet>();
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
                thePlanets.push(new Planet(values[1], values[0]));
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
    }

    private static void printPlanets (Stack<Planet> solarSystem)
    {
        Planet thePlanet = null;

        while (!solarSystem.empty())
        {
            thePlanet = solarSystem.pop();

            System.out.println(thePlanet);
        }
    }
}