import java.io.*;
import java.util.Set;
import java.util.HashSet;

/**
 * Take the data given and find where the two wires cross.
 * 
 * The size of the circuit board should have been obtained
 * correctly beforehand using something like CircuitSize or
 * TestPlotter. The algorithms which those programs use
 * could be incorporated directly here but for now we'll keep
 * things simple.
 * 
 * This will use the CircuitBoard against known example data
 * and compare with the expected output.
 */

public class Verifier
{
    public static final String SEPARATOR = ",";

    // default circuit board size

    public static final int EXAMPLE1_RESULT = 159;
    public static final int EXAMPLE2_RESULT = 135;
    public static final int EXAMPLE3_RESULT = 6;

    public static void main (String[] args)
    {
        boolean dump = false;
        String fileToUse = EXAMPLE1;
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("[-help] [-dump] [-debug] [-example1] [-example2] [-example3]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-dump".equals(args[i]))
                dump = true;

            if ("-example1".equals(args[i]))
                fileToUse = EXAMPLE1;

            if ("-example2".equals(args[i]))
                fileToUse = EXAMPLE2;


            if ("-example3".equals(args[i]))
                fileToUse = EXAMPLE3;
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
            reader = new BufferedReader(new FileReader(fileToUse));
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

                        if ((overlaps != null) && overlaps.size() > 0)
                        {
                            int result = _theBoard.getDistance(overlaps);

                            if (fileToUse.equals(EXAMPLE1))
                            {
                                if (result == EXAMPLE1_RESULT)
                                    System.out.println("Verified ok!");
                                else
                                    System.out.println("Verify failed!");
                            }
                            else
                            {
                                if (fileToUse.equals(EXAMPLE2))
                                {
                                    if (result == EXAMPLE2_RESULT)
                                        System.out.println("Verified ok!");
                                    else
                                        System.out.println("Verify failed!");
                                }
                                else
                                {
                                    if (result == EXAMPLE3_RESULT)
                                        System.out.println("Verified ok!");
                                    else
                                        System.out.println("Verify failed!");
                                }
                            }
                        }
                        else
                            System.out.println("There were no overlaps.");
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

    /*
     * Let's try something different this time with verifying the
     * program works. Rather than embed verification within and potentially
     * duplicate code, we'll have the examples in their own files and load
     * them separately, compare with the expected result and give a
     * true/false outcome accordingly.
     */

    private static final String EXAMPLE1 = "example1.txt";
    private static final String EXAMPLE2 = "example2.txt";
    private static final String EXAMPLE3 = "example3.txt";
}