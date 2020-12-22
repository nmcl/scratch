import java.io.*;

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

    public static final int EXAMPLE1_LENGTH = 537;
    public static final int EXAMPLE1_WIDTH = 285;
    public static final int EXAMPLE1_RESULT = 159;

    public static final int EXAMPLE2_LENGTH = 369;
    public static final int EXAMPLE2_WIDTH = 216;
    public static final int EXAMPLE2_RESULT = 135;

    public static final int EXAMPLE3_LENGTH = 17;
    public static final int EXAMPLE3_WIDTH = 16;
    public static final int EXAMPLE3_RESULT = 6;

    public static void main (String[] args)
    {
        boolean dump = false;
        int length = EXAMPLE1_LENGTH;
        int width = EXAMPLE1_WIDTH;
        String fileToUse = EXAMPLE1;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("[-help] [-dump] [-example1] [-example2] [-example3]");
                System.exit(0);
            }

            if ("-dump".equals(args[i]))
                dump = true;

            if ("-example1".equals(args[i]))
                fileToUse = EXAMPLE1;

            if ("-example2".equals(args[i]))
            {
                fileToUse = EXAMPLE2;

                length = EXAMPLE2_LENGTH;
                width = EXAMPLE2_WIDTH;
            }

            if ("-example3".equals(args[i]))
            {
                fileToUse = EXAMPLE3;

                length = EXAMPLE3_LENGTH;
                width = EXAMPLE3_WIDTH;
            }
        }

        _theBoard = new CircuitBoard(length, width);

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
                if (_theBoard.plotLine(line1))
                {
                    if (_theBoard.plotLine(line2))
                    {
                        _theBoard.printBoard();
                        
                        int result = _theBoard.getDistance();

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