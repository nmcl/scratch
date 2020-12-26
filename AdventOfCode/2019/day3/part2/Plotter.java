import java.io.*;
import java.util.Set;
import java.util.HashSet;

/*
 * Take the data given and find where the two wires cross.
 * 
 * The size of the circuit board should have been obtained
 * correctly beforehand using something like CircuitSize or
 * TestPlotter. The algorithms which those programs use
 * could be incorporated directly here but for now we'll keep
 * things simple.
 */

public class Plotter
{
    public static final String SEPARATOR = ",";

    public static void main (String[] args)
    {
        boolean dump = false;
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-help] [-dump] [-debug]");
                System.exit(0);
            }

            if ("-dump".equals(args[i]))
                dump = true;

            if ("-debug".equals(args[i]))
                debug = true;
        }

        _theBoard = new CircuitBoard(debug);

        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        String[] line1 = null;
        String[] line2 = null;

        try
        {
            reader = new BufferedReader(new FileReader(DATA_FILE));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                if (line1 == null)
                    line1 = line.split(SEPARATOR);
                else
                {
                    if (line2 == null)
                        line2 = line.split(SEPARATOR);
                    else
                    {
                        System.out.println("Unexpected extra lines in input file!");

                        System.exit(0);
                    }
                }
            }

            if (dump)
                dumpData(line1, line2);
            else
            {
                Set<Coordinate> firstLine = new HashSet<Coordinate>();
                Set<Coordinate> secondLine = new HashSet<Coordinate>();

                if (_theBoard.plotLine(line1, firstLine))
                {
                    if (debug)
                        _theBoard.printCircuit(firstLine);

                    if (_theBoard.plotLine(line2, secondLine))
                    {
                        if (debug)
                            _theBoard.printCircuit(secondLine);
                        
                        Set<Coordinate> overlaps = _theBoard.getOverlaps(firstLine, secondLine);

                        System.out.println("Manhattan distance is: "+_theBoard.getManhattanDistance(overlaps));
                        System.out.println("Minimum overlap distance is: "+_theBoard.getMinimumDistance(overlaps, line1, line2));
                    }
                    else
                        System.out.println("Error in plotting second line!");
                }
                else
                    System.out.println("Error in plotting first line!");
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
    }

    private static final void dumpData (String[] line1, String[] line2)
    {
        System.out.println("Wire 1:");
        for (String str : line1)
        {
            System.out.println(str);
        }

        System.out.println("\nWire 2:");
        for (String str : line2)
        {
            System.out.println(str);
        }
    }

    private static CircuitBoard _theBoard = null;

    private static final String DATA_FILE = "data.txt";
}