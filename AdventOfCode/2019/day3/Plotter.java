import java.io.*;

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

    // default circuit board size

    public static final int DEFAULT_LENGTH = 23305;
    public static final int DEFAULT_WIDTH = 14050;

    public static void main (String[] args)
    {
        boolean dump = false;
        int length = DEFAULT_LENGTH;
        int width = DEFAULT_WIDTH;
        String fileToUse = DATA_FILE;
        int expectedResult = -1;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("[-help] [-verify] [-dump] [-length <length>] [-width <width>] [-example1 <result>] [-example2 <result>]");
                System.exit(0);
            }

            if ("-dump".equals(args[i]))
                dump = true;

            if ("-width".equals(args[i]))
                _width = Integer.parseInt(args[i+1]);

            if ("-length".equals(args[i]))
                _length = Integer.parseInt(args[i+1]);

            if ("-example1".equals(args[i]))
            {
                fileToUse = EXAMPLE1;

                expectedResult = Integer.parseInt(args[i+1]);
            }

            if ("-example2".equals(args[i]))
            {
                fileToUse = EXAMPLE2;

                expectedResult = Integer.parseInt(args[i+1]);
            }
        }

        _theBoard = new int[_length][_width];

        for (int i = 0; i < _length; i++)
        {
            for (int j = 0; j < _width; j++)
                _theBoard[i][j] = 0;
        }

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
                if (plotLine(line1))
                {
                    if (plotLine(line2))
                    {
                        printBoard();
                        
                        int result = getDistance();

                        if (expectedResult != -1)
                        {
                            if (expectedResult == result)
                                System.out.println("Verified ok!");
                            else
                                System.out.println("Verify failed!");
                        }
                        else
                            System.out.println("Manhattan distance: "+result);
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

    /*
     * Let's try something different this time with verifying the
     * program works. Rather than embed verification within and potentially
     * duplicate code, we'll have the examples in their own files and load
     * them separately, compare with the expected result and give a
     * true/false outcome accordingly.
     */

    private static final String EXAMPLE1 = "example1.txt";
    private static final String EXAMPLE2 = "example2.txt";
    private static final int EXAMPLE1_DISTANCE = 159;
    private static final int EXAMPLE2_DISTANCE = 135;
}