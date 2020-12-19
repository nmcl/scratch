import java.io.*;

public class TestPlotter
{
    public static final String SEPARATOR = ",";

    public static final UP = "U";
    public static final DOWN = "D";
    public static final LEFT = "L";
    public static final RIGHT = "R";

    /*
     * Assume the starting point is in the middle of the
     * 2d array. But how big should the array be to ensure
     * we don't run out of space or over the edges?
     * 
     * We take a guess at the moment but could create an
     * iterative program to dynamically assess the minimum
     * size needed.
     */

    public static final int LENGTH = 1000;
    public static final int WIDTH = 1000;

    public static void main (String[] args)
    {
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("[-help] [-debug]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;
        }

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

            if (debug)
                dumpData(line1, line2);
            else
                checkMatrixSize(line2, line2);
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

    private static final String DATA_FILE = "data.txt";
}