import java.util.*;
import java.io.*;

public class RemoteControl
{
    public static final String INSTRUCTIONS = "instructions.txt";

    public static void main (String[] args)
    {
        boolean debug = false;
        boolean stepsToOxygen = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-debug] [-steps] [-help]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-steps".equals(args[i]))
                stepsToOxygen = true;
        }

        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        String[] values = null;

        try
        {
            reader = new BufferedReader(new FileReader(INSTRUCTIONS));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                values = line.split(Intcode.DELIMITER);
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

        Vector<String> instructions = new Vector<String>();

        instructions.addAll(Arrays.asList(values));

        RepairDroid droid = new RepairDroid(instructions, debug);

        if (stepsToOxygen)
        {
            int steps = droid.moveToOxygenStation();

            System.out.println("Number of steps to reach oxygen station: "+steps);
        }
        else
        {
            droid.mapEntireMaze();

            System.out.println(droid.getMaze());

            Coordinate coord = droid.getMaze().getOxygenStation();
            int[] dimensions = droid.getMaze().getDimensons();

            System.out.println("Oxygen station: "+coord);
            System.out.println("Maze dimensions: <"+dimensions[0]+", "+dimensions[2]+"> <"+dimensions[1]+", "+dimensions[3]+">");

            OxygenFiller filler = new OxygenFiller(droid.getMaze(), debug);

            System.out.println("\nNumber of minutes to fill space with oxygen: "+filler.fillWithOxygen());
        }
    }
}